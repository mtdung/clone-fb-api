package vn.edu.fpt.horo.dto.request.profile;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import vn.edu.fpt.horo.dto.response.file.FileResponse;

import java.time.LocalDate;
import java.util.UUID;

/**
 * vn.edu.fpt.horo.dto.request.profile
 *
 * @author : Portgas.D.Ace
 * @created : 01/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Setter
@ToString
@SuperBuilder
public class GetProfileBookingRespone {
    private String profileId = UUID.randomUUID().toString();
    private FileResponse avatar;
}
