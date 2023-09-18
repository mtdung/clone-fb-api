package vn.edu.fpt.horo.dto.request.advisor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.constant.Gender;

import java.time.LocalDate;
import java.util.List;


/**
 * vn.edu.fpt.accounts.mapper
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateAdvisorRequest {
    private String summary;
    private String description;
    private String avatar;
}
