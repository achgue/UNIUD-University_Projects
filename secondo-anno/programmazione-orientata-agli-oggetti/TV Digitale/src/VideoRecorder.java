import javax.management.InstanceAlreadyExistsException;
import java.rmi.NoSuchObjectException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class VideoRecorder {
    ArrayList<Broadcast> recordingList = null;
    private ArrayList<Broadcast> allFutureBroadcasts = null;

    public ArrayList<Broadcast> getAllFutureBroadcasts() {
        Iterator<Channel> channelIterator = ChannelsList.channels.iterator();
        while (channelIterator.hasNext()){
            Iterator<Broadcast> broadcastIterator = channelIterator.next().getBroadcastList().iterator();
            while (broadcastIterator.hasNext()){
                Broadcast current = broadcastIterator.next();
                if (current.schedule.isAfter(LocalDateTime.now())){
                    allFutureBroadcasts.add(current); //@todo sort list
                }
            }
        }
        return allFutureBroadcasts;
    }

    /**
     *
     * @param broadcast
     * @throws InstanceAlreadyExistsException
     * @throws NoSuchObjectException
     */
    public void addBroadcastToRecordingList(Broadcast broadcast) throws InstanceAlreadyExistsException, NoSuchObjectException {
        Broadcast b = findSameBroadcast(broadcast);
        Iterator<Broadcast> broadcastIterator = recordingList.iterator();
        while (broadcastIterator.hasNext()){
            Broadcast current = broadcastIterator.next();
            if (current.equals(b)){
                throw new InstanceAlreadyExistsException("This broadcast has already been added to the recording list.");
            }
        }
        recordingList.add(b);
    }

    /**
     *
     * @param broadcast
     * @return
     * @throws NoSuchObjectException
     */
    public Broadcast findSameBroadcast(Broadcast broadcast) throws NoSuchObjectException {
        Iterator<Broadcast> broadcastIterator = getAllFutureBroadcasts().iterator();
        while (broadcastIterator.hasNext()){
            Broadcast current = broadcastIterator.next();
            if (current.equals(broadcast)){
                return current;
            }
        }
        throw new NoSuchObjectException("This broadcast doesn't exist.");
    }


    /**
     *
     * @param title
     * @param channel
     * @param schedule
     * @return
     * @throws NoSuchObjectException
     */
    public Broadcast searchBroadcast(String title, Channel channel, LocalDateTime schedule) throws NoSuchObjectException {
        Broadcast b = new Broadcast(title, schedule, channel);
        return findSameBroadcast(b);
    }

    public void deleteFromRecordingList(Broadcast broadcast) throws NoSuchObjectException {
        Broadcast b = findSameBroadcast(broadcast);
        recordingList.remove(recordingList.get(recordingList.indexOf(b))); //@todo can be found with broadcast?
    }


}

