package vn.edu.fpt.horo.dto.request.profile;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.config.datetime.CustomDateTimeDeserializer;
import vn.edu.fpt.horo.config.datetime.CustomDateTimeSerializer;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * vn.edu.fpt.accounts.dto.request.profile
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateProfileRequest implements Serializable {

    private static final long serialVersionUID = 3780717543344413848L;
    private String accountId;
    private String gender;
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private LocalDateTime dateOfBirth;
    private String address;
    private String phoneNumber;
    private String avatar;
    private String description;
}
