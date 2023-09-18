package vn.edu.fpt.horo.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum _ConfigType {

    STRING("String"),
    INTEGER("Integer"),
    DOUBLE("Double");

    private final String type;

}
