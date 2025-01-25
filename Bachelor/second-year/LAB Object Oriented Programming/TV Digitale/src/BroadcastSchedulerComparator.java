import java.util.Comparator;

/**
 *
 */
public class BroadcastSchedulerComparator implements Comparator<Broadcast> {
    @Override
    public int compare(Broadcast o1, Broadcast o2) {
        return o1.schedule.compareTo(o2.schedule);
    }
}