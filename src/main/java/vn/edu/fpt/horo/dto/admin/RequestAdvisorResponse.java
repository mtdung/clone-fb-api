package vn.edu.fpt.horo.dto.admin;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class RequestAdvisorResponse {
    private String     ticketId;
    private String     advisorId;
    private String     accountName;
    private String     cardId;
    private String     type;
    private String     issuedPlace;
    private LocalDate issuedDate;
    private Boolean     status;
    private String     issuedBy;
    private String     gender;
    private String     fontIdImageLink;
    private String     backIdImageLink;
    private String     portraitImageLink;
    private String     summary;
    private String     description;
}
