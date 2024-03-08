package vn.edu.fpt.fb.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * vn.edu.fpt.accounts.utils
 **/

public class ParamUtils {

    public static String replaceParams(String message, Map<String, String> params){
        List<String> paramInMessage = getParamInMessage(message);
        return paramInMessage.stream().reduce(message, (m, v) -> m.replace(addInFix(v), params.get(v)));
    }

    private static List<String> getParamInMessage(String message){
        String[] messages = message.split(" ");
        return Arrays.stream(messages).filter(v -> v.matches(AppRegex.PARAM_REGEX)).map(v -> v.replace("%%", "")).collect(Collectors.toList());
    }

    private static String addInFix(String param){
        return AppRegex.PARAM_INFIX + param + AppRegex.PARAM_INFIX;
    }
}
