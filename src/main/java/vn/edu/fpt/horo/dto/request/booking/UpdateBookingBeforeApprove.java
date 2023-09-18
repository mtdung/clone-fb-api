package vn.edu.fpt.horo.dto.request.booking;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.config.datetime.CustomDateTimeDeserializer;
import vn.edu.fpt.horo.config.datetime.CustomDateTimeSerializer;

import java.time.LocalDateTime;

/**
 * vn.edu.fpt.horo.dto.request.booking
 *
 * @author : Portgas.D.Ace
 * @created : 19/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateBookingBeforeApprove {
    private Integer slot;
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private LocalDateTime bookingTime;
}
