import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

/**
 * ADT for Broadcast:
 * It groups information about a single broadcast: title, genre, default and other languages, schedule and referred channel.
 * Same broadcast can be referred to only one channel.
 */
public class Broadcast {

    String title;
    String genre;
    //private infotab
    Language defaultLanguage;
    //@TODO private ArrayList<Language> otherLanguages; //contains only available languages
    LocalDateTime schedule;
    Channel channel;

    Broadcast(String title, LocalDateTime schedule, Channel channel){
        this.title = title;
        this.schedule = schedule;
        this.channel = channel;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Broadcast broadcast = (Broadcast) o;
        return Objects.equals(title, broadcast.title) && Objects.equals(schedule, broadcast.schedule) && Objects.equals(channel, broadcast.channel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, schedule, channel);
    }
}
