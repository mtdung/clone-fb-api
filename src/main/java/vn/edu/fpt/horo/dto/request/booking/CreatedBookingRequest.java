package vn.edu.fpt.horo.dto.request.booking;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.config.datetime.CustomDateTimeDeserializer;
import vn.edu.fpt.horo.config.datetime.CustomDateTimeSerializer;
import vn.edu.fpt.horo.constant.BookingType;
import vn.edu.fpt.horo.dto.request.promotion.PromotionConditionDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreatedBookingRequest {
    private BookingType bookingType;
    private String advisorId;
    private Integer slot;
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private LocalDateTime bookingTime;
    private String problem;
    private String topicId;
    private String servicePackageId;
    private PromotionConditionDTO promotion;
}
