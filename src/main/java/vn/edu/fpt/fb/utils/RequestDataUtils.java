package vn.edu.fpt.fb.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;

/**
 * vn.edu.fpt.accounts.utils
 **/

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RequestDataUtils {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";

    public static String generatePassword(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(index));
        }
        return sb.toString();
    }

    public static String convertSearchableData(String data){
        if(Objects.nonNull(data)) {
            return "^(.)*(" + data + ")(.)*$";
        }else{
            return null;
        }
    }

    public static Boolean convertSearchableData(Boolean bool){
        if(Objects.nonNull(bool)){
            return bool;
        }else{
            return true;
        }
    }

    public static LocalDateTime convertDateFrom(String dateFrom){
        if(Objects.isNull(dateFrom) || dateFrom.isBlank()){
            return LocalDateTime.of(1900, 1, 1,0, 0, 0);
        }else{
            return DateTimeConverter.toLocalDateTime(dateFrom);
        }
    }

    public static LocalDateTime convertDateTo(String dateTo){
        if(Objects.isNull(dateTo) || dateTo.isBlank()){
            return LocalDateTime.now();
        }else{
            return DateTimeConverter.toLocalDateTime(dateTo);
        }
    }

    public static boolean isNullOrEmptyString(String... strings) {
        for (String str : strings) {
            if (str == null || str.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
