package vn.edu.fpt.horo.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * vn.edu.fpt.accounts.dto.common
 *
 * @author : Portgas.D.Ace
 * @created : 24/03/2023
 * @contact : 0339850697- congdung2510@gmail.com
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum ApplicationStatusEnum {

    WAITING_FOR_APPROVE("WAITING_FOR_APPROVAL"),
    APPROVED("APPROVED"),
    REJECTED("REJECTED"),
    ;

    private String statusName;

    ApplicationStatusEnum(String statusName) {
        this.statusName = statusName;
    }
}
