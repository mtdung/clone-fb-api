package vn.edu.fpt.horo.dto.request.booking;

import lombok.Data;

import java.io.Serializable;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Data
public class UpdateBookingStatusRequest implements Serializable {
    private static final long serialVersionUID = -7953394343518162390L;
    private String bookingId;
    private String status;
}
