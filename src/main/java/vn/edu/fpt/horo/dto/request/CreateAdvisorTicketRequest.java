package vn.edu.fpt.horo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.constant.Gender;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateAdvisorTicketRequest implements Serializable {
    private static final long serialVersionUID = 9127691414063449939L;
    private String cardId;
    private String issuedPlace;
    private LocalDate issuedDate;
    private String issuedBy;
    private Gender gender;
    private String fontIdImage;
    private String backIdImage;
    private String portraitImage;
    private String summary;
    private String description;
    private String request;
    private Integer experience;
}
