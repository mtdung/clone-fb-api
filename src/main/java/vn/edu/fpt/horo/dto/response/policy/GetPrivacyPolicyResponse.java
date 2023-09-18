package vn.edu.fpt.horo.dto.response.policy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.edu.fpt.horo.dto.response.file.FileResponse;

import java.io.Serializable;

/**
 * vn.edu.fpt.horo.dto.response.policy
 *
 * @author : Portgas.D.Ace
 * @created : 02/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetPrivacyPolicyResponse implements Serializable {
    private static final long serialVersionUID = 7731998426905407232L;
    private FileResponse pdfPrivacyPolicyResponse;
}
