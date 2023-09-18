package vn.edu.fpt.horo.dto.response.booking;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * vn.edu.fpt.horo.dto.response.booking
 *
 * @author : Portgas.D.Ace
 * @created : 08/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NumberBookingResponse implements Serializable {
    private Integer canceled;
    private Integer completed;
    private Integer waitingForApproval;
    private Integer waitingForCompleted;
    private Integer approved;
    private Integer processing;
    private Integer rejected;
    private Integer timeout;
    private Integer notJoined;
    private Integer numberRate;
}
