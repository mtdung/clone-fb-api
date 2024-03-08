package vn.edu.fpt.fb.config.datetime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;

import static vn.edu.fpt.fb.utils.CustomDateTimeFormatter.*;

/**
 * vn.edu.fpt.accounts.config.datetime
 **/

public class CustomDateTimeSerializer extends StdSerializer<LocalDateTime> {

    public CustomDateTimeSerializer(){
        this(null);
    }

    public CustomDateTimeSerializer(Class<LocalDateTime> t) {
        super(t);
    }

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

//        jsonGenerator.writeString(localDateTime.format(DATE_TIME_FORMATTER));
//        jsonGenerator.writeString(localDateTime.format(DATE_FORMATTER));
        jsonGenerator.writeString(localDateTime.format(DATE_TIME_HORO));

    }
}
