package vn.edu.fpt.horo.entity;

import lombok.*;
import vn.edu.fpt.horo.constant._ConfigType;
import vn.edu.fpt.horo.entity.common.Auditor;

import javax.persistence.*;
import java.util.UUID;

/**
 * vn.edu.fpt.accounts.entity
 *
 * @author : Portgas.D.Ace
 * @created : 17/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/

@Table(name = "app_configs")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AppConfig extends Auditor {
    private static final long serialVersionUID = 1783659661056067488L;
    @Id
    @Builder.Default
    private String configId = UUID.randomUUID().toString();
    private String configKey;
    private String configValue;
    @Enumerated(EnumType.STRING)
    private _ConfigType configType;


}
