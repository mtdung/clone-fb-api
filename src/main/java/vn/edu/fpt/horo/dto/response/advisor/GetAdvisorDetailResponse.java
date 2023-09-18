package vn.edu.fpt.horo.dto.response.advisor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.constant.AdvisorStatus;
import vn.edu.fpt.horo.constant.Gender;
import vn.edu.fpt.horo.dto.response.account.GetAccountDetailResponse;
import vn.edu.fpt.horo.dto.response.file.FileResponse;
import vn.edu.fpt.horo.entity.Rate;
import vn.edu.fpt.horo.entity.Topic;
import vn.edu.fpt.horo.entity._PackageService;
import vn.edu.fpt.horo.service.PackageService;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
public class GetAdvisorDetailResponse implements Serializable {
    private static final long serialVersionUID = 4173342245230073169L;
    private String advisorId;
    private GetAccountDetailResponse account;
    private String cardId;
    private String issuedPlace;
    private LocalDate issuedDate;
    private String issuedBy;
    private Gender gender;
    private FileResponse fontIdImage;
    private FileResponse backIdImage;
    private FileResponse portraitImage;
    private String summary;
    private String description;
    private _PackageService packageServices;
    private BigDecimal avgPrice;
    private Float totalRate;
    private Integer experience;
    private AdvisorStatus status;
}
