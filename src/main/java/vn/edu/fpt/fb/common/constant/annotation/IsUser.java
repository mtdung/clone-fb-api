package vn.edu.fpt.fb.common.constant.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * vn.edu.fpt.accounts.config.security.annotation
 **/

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@PreAuthorize("hasAnyRole('USER', 'MANAGER', 'ADMIN')")
public @interface IsUser {
}
