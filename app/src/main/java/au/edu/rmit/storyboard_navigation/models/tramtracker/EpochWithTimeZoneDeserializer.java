package au.edu.rmit.storyboard_navigation.models.tramtracker;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class EpochWithTimeZoneDeserializer extends StdDeserializer<EpochWithTimeZone> {
    public EpochWithTimeZoneDeserializer() {
        this(null);
    }

    public EpochWithTimeZoneDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public EpochWithTimeZone deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String epoch_string = node.toString();
        //String epoch_string = node.get("").toString();
        epoch_string = epoch_string.replace("\"/Date(", "");
        epoch_string = epoch_string.replace(")/\"", "");

        //System.out.println(epoch_string);

        return new EpochWithTimeZone(epoch_string);
    }
}
