package vn.edu.fpt.horo.entity;

import lombok.*;
import org.springframework.context.annotation.Lazy;
import vn.edu.fpt.horo.constant.BookingStatus;
import vn.edu.fpt.horo.constant.BookingType;
import vn.edu.fpt.horo.dto.response.advisor.GetAdvisorResponse;
import vn.edu.fpt.horo.entity.common.Auditor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.entity
 *
 * @author : Portgas.D.Ace
 * @created : 20/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@Table(name = "bookings")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Booking extends Auditor {
    @Id
    @Builder.Default
    private String bookingId = UUID.randomUUID().toString();
    private BigDecimal coin;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
    private LocalDateTime bookingTime;
    private Integer slot;
    @Enumerated(EnumType.STRING)
    private BookingType type;
    @ManyToOne
    @Lazy
    private Advisor advisor;
    @ManyToOne
    @Lazy
    private Topic topic;
    @ManyToOne
    @Lazy
    private _PackageService packageService;
    private String reason;
    private String problem;


}
