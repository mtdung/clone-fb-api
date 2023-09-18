package vn.edu.fpt.horo.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * vn.edu.fpt.accounts.utils
 **/

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthenticationUtils {
    public static String addRolePrefix(String role) {
        return "ROLE_" + role;
    }
    public static String addPermissionPrefix(String role, String permission) {
        return role + ":" + permission;
    }
}
