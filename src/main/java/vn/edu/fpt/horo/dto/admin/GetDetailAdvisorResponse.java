package vn.edu.fpt.horo.dto.admin;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class GetDetailAdvisorResponse {
    private String advisorId;
    private String accountName;
    private String email;
    private String phoneNumber;
    private String cardId;
    private String issuedPlace;
    private LocalDate issuedDate;
    private String issuedBy;
    private String gender;
    private String fontIdImageLink;
    private String backIdImageLink;
    private String portraitImageLink;
}
