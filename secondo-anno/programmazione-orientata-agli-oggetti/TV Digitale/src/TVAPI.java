import javax.management.InstanceAlreadyExistsException;
import java.rmi.NoSuchObjectException;
import java.time.LocalDateTime;


public class TVAPI {
    private TVPlayer player;
    private VideoRecorder recorder;

    /**
     * Play channel as main, or secondary if two in one is on.
     * @param channel channel to watch
     */
    public void watch(Channel channel){
        player.playChannel(channel);
    }

    /**
     * Turns on or off two in one mode
     * @param isEnabled if true turns on else turns off two in one mode
     */
    public void switchTwoInOne(boolean isEnabled){
        if (isEnabled){
            player.enableTwoInOne();
        }else{
            player.disableTwoInOne();
        }
    }

    /**
     * Adds broadcast to channel broadcast queue
     * @param channel channel to add the broadcast to
     * @param broadcast broadcast to add
     * @throws InvalidTimeException is thrown if broadcast time is before current time
     * @throws NotSameChannelException if broadcast channel doesn't match channel
     */
    public void addBroadcastTo(Channel channel, Broadcast broadcast) throws InvalidTimeException, NotSameChannelException {
        ChannelsList.channels.get(ChannelsList.channels.indexOf(channel)).addBroadcast(broadcast);
    }

    /**
     * Adds broadcast we are looking for to the recording queue
     * @param title title of the broadcast
     * @param channel channel of the broadcast
     * @param schedule time and date scheduled for the broadcast
     * @throws NoSuchObjectException if the broadcast is not in the recording queue
     * @throws InstanceAlreadyExistsException if the broadcast is already in the recording queue
     */
    public void addBroadcastToRecordingQueue(String title, Channel channel, LocalDateTime schedule) throws NoSuchObjectException, InstanceAlreadyExistsException {
        if(title == null || channel == null || schedule == null){
            throw new IllegalArgumentException("Null value not accepted as argument");
        }
        Broadcast b = recorder.searchBroadcast(title, channel, schedule);
        recorder.addBroadcastToRecordingList(b);
    }

    /**
     * Verifies si the broadcast we are looking for is inside the recording queue
     * @param title title of the broadcast
     * @param channel channel of the broadcast
     * @param schedule time and date scheduled for the broadcast
     * @return true if broadcast is scheduled for the recording false in the other cases
     * @throws IllegalArgumentException if one of the arguments is null
     * @throws NoSuchObjectException if the broadcast is not in the recording queue
     */
    public boolean isInRecordingList(String title, Channel channel, LocalDateTime schedule) throws NoSuchObjectException {
        if(title == null || channel == null || schedule == null){
            throw new IllegalArgumentException("Null value not accepted as argument");
        }
        Broadcast b = recorder.searchBroadcast(title, channel, schedule);
        if(recorder.recordingList.get(recorder.recordingList.indexOf(b))!=null){
            return true;
        }
        return false;
    }

    /**
     * Searches a Broadcast by his title, channel and schedule and deletes it from the recoding queue
     * @param title title of the broadcast
     * @param channel channel of the broadcast
     * @param schedule time and date scheduled for the broadcast
     * @throws NoSuchObjectException if the broadcast is not in the recording queue
     */
    public void removeBroadcastFromRecordingQueue(String title, Channel channel, LocalDateTime schedule) throws NoSuchObjectException {
        if(title == null || channel == null || schedule == null){
            throw new IllegalArgumentException("Null value not accepted as argument");
        }
        Broadcast b = recorder.searchBroadcast(title, channel, schedule);
        recorder.deleteFromRecordingList(b);
    }


}
