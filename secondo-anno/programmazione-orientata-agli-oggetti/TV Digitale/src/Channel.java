import java.time.LocalDateTime;
import java.util.*;

/**
 * ADT for Channel:
 * It groups information about a single channel: name, program number, list of broadcasts.
 * It manages adding of broadcasts: public void addBroadcast(Broadcast broadcast).
 */
public class Channel { //programma televisivo da vedere
    public String name;
    public int number;
    private ArrayList<Broadcast> broadcastList;

    /**
     * ABSTRACT FUNCTION: list of broadcast is represented by this.broadcastList
     * name represents channel name, number represents program number
     *
     * INVARIANT: this.name != null, this.number > 0, broadcastList != null but can be empty
     */

    Channel(String name, int number){
        this.name = name;
        this.number = number;
        broadcastList = new ArrayList<Broadcast>();
    }

    public ArrayList<Broadcast> getBroadcastList() {
        return broadcastList;
    }


    public void addBroadcast(Broadcast broadcast) throws    InvalidTimeException, NotSameChannelException {
        if(broadcast.schedule.isBefore(LocalDateTime.now())){
            throw new InvalidTimeException("Invalid broadcast time.");
        }
        if(broadcast.channel.equals(this)){
            broadcastList.add(broadcast);
            Collections.sort(broadcastList, new BroadcastSchedulerComparator());
        }else{
            throw new NotSameChannelException("Broadcast channel should match to this channel.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Channel channel = (Channel) o;
        return number == channel.number && Objects.equals(name, channel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, number);
    }
}
