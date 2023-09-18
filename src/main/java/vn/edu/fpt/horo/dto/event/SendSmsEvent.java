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
public class SendSmsEvent implements Serializable {

    private static final long serialVersionUID = 893856313535683110L;
    private String templateId;
    private String sendTo;
    private Map<String, String> params;
}
