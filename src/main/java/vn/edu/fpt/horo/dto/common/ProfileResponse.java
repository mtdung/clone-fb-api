package vn.edu.fpt.horo.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.dto.response.file.FileResponse;

import java.time.LocalDateTime;
import java.util.UUID;


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
public class ProfileResponse {

    private String profileId = UUID.randomUUID().toString();
    private String gender;
    private LocalDateTime dateOfBirth;
    private String address;
    private String phoneNumber;
    private FileResponse avatar;
    private String description;
    private FileResponse horo;

}
