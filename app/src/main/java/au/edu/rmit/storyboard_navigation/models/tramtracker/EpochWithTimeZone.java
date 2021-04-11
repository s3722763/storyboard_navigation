package au.edu.rmit.storyboard_navigation.models.tramtracker;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@JsonDeserialize(using = EpochWithTimeZoneDeserializer.class)
public class EpochWithTimeZone {
    String epoch_string;
    ZonedDateTime datetime;

    public EpochWithTimeZone(String epoch_string) {
        this.epoch_string = epoch_string;

        epoch_string = epoch_string.replace("+", "~+");
        String[] parts = epoch_string.split("~");
        long epoch = Long.parseLong(parts[0]);
        //System.out.println(epoch);
        this.datetime = Instant.ofEpochMilli(epoch).atZone(ZoneId.of(parts[1]));
        //System.out.println(datetime.toString());
    }

    public ZonedDateTime getDatetime() {
        return this.datetime;
    }
}
