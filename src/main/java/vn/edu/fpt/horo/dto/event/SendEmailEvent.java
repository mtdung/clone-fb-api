package vn.edu.fpt.horo.dto.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * vn.edu.fpt.accounts.dto.event
 **/

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SendEmailEvent implements Serializable {

    private static final long serialVersionUID = -5953051192045222255L;
    private String templateId;
    private String sendTo;
    private String bcc;
    private String cc;
    private Map<String, String> params;
}
