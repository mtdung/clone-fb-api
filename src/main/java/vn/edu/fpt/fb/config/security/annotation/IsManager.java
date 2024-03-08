package vn.edu.fpt.fb.config.security.annotation;

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
@PreAuthorize("hasAnyRole('MANAGER', 'ADMIN')")
public @interface IsManager {
}
