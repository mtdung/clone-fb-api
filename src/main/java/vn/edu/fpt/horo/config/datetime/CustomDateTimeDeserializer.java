package vn.edu.fpt.horo.config.datetime;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDateTime;

import static vn.edu.fpt.horo.utils.CustomDateTimeFormatter.DATE_TIME_HORO;

/**
 * vn.edu.fpt.accounts.config.datetime
 **/

public class CustomDateTimeDeserializer extends StdDeserializer<LocalDateTime> {

    public CustomDateTimeDeserializer(){

        this(null);
    }

    protected CustomDateTimeDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        return LocalDateTime.parse(jsonParser.getText(), DATE_TIME_HORO);
    }
}
