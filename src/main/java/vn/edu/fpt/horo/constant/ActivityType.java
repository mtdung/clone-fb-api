package vn.edu.fpt.horo.constant;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * vn.edu.fpt.accounts.constant
 **/

@Getter
@NoArgsConstructor
public enum ActivityType {

    COMMENT("COMMENT"),
    HISTORY("HISTORY");

    private String type;

    ActivityType(String type) {
        this.type = type;
    }
}
