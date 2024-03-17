package vn.edu.fpt.fb.common.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author namlh4
 */
public class AccountConstant {
    public static final String ADMIN_USERNAME = "admin";
    public static final String ADMIN_EMAIL = "codeteamfpt@gmail.com";
    public static final String ADMIN_PASSWORD = "admin@123";
    public static final String ADMIN_PHONE = "0383668730";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_NORMAL_USER = "USER";
    public static final String ROLE_MANAGER = "MANAGER";
    public static final List<String> DEFAULT_ROLE = Arrays.asList(ROLE_ADMIN, ROLE_NORMAL_USER, ROLE_MANAGER);
}
