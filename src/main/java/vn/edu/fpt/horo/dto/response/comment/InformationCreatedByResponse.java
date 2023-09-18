package vn.edu.fpt.horo.dto.response.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.dto.response.file.FileResponse;

/**
 * vn.edu.fpt.horo.dto.response.comment
 *
 * @author : Portgas.D.Ace
 * @created : 07/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class InformationCreatedByResponse {
    private String accountId;
    private String username;
    private ProfileResponseCustom profile;
    private String fullName;
}
