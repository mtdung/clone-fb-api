package vn.edu.fpt.horo.dto.response.booking;

import vn.edu.fpt.horo.constant.BookingStatus;
import vn.edu.fpt.horo.constant.BookingType;
import vn.edu.fpt.horo.entity.Advisor;
import vn.edu.fpt.horo.entity._PackageService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
public class GetBookingResponse implements Serializable {
    private static final long serialVersionUID = 1880195746838067867L;
    private String bookingId;
    private Advisor advisor;
    private _PackageService packageService;
    private BigDecimal coin;
    private BookingStatus status;
    private LocalDateTime bookingTime;
    private Integer slot;
    private BookingType type;
    private String reason;
    private String problem;
}
