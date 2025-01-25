import java.util.ArrayList;
import java.util.Arrays;

/**
 * ADT
 */
public final class ChannelsList {

    static ArrayList<Channel> channels = new ArrayList<>(Arrays.asList(
            new Channel("Rai1", 1),
            new Channel("Rai2", 2),
            new Channel("Rai3", 3),
            new Channel("Rete4", 4),
            new Channel("Tele5", 5),
            new Channel("Italia1", 6)
    ));
}
