package vn.edu.fpt.horo.dto.request.policy;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * vn.edu.fpt.horo.dto.request.policy
 *
 * @author : Portgas.D.Ace
 * @created : 02/04/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreatePolicyRequest implements Serializable {
    private static final long serialVersionUID = -436586549626668311L;
    private String policyName;
    private String filePolicy;
}
