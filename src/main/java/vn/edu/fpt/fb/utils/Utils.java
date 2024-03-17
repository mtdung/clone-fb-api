package vn.edu.fpt.fb.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import vn.edu.fpt.fb.common.constant.ResponseStatusEnum;
import vn.edu.fpt.fb.exception.BusinessException;

public class Utils {
    public static void throwException(ResponseStatusEnum status, String message, boolean condition) {
        if (condition) {
            throw new BusinessException(status, message);
        }
    }
}
