package vn.edu.fpt.horo.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum PostStatusEnum {
    OPEN("OPEN"),
    CLOSE("CLOSE")
    ;

    private String statusName;

    PostStatusEnum(String statusName) {
        this.statusName = statusName;
    }
}
