package vn.edu.fpt.horo.dto.response.advisor;

import lombok.*;
import lombok.experimental.SuperBuilder;
import vn.edu.fpt.horo.constant.ApplicationStatusEnum;
import vn.edu.fpt.horo.dto.common.AuditableResponse;

import java.awt.*;
import java.time.LocalDate;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class GetApplicationDetailResponse extends AuditableResponse {

    private static final long serialVersionUID = 8208749423480067583L;
    private String applicationId;
    private String accountId;
    private ApplicationStatusEnum status;
    private Image image;
    private String cardId;
    private String regularAddress;
    private LocalDate cardDate;
    private String issuedBy;
}
