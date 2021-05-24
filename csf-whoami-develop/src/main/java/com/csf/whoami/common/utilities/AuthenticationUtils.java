/**
 *
 */
package com.csf.whoami.common.utilities;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.csf.whoami.backend.entity.UserInfo;
import com.csf.whoami.backend.exception.CustomException;
import com.csf.whoami.backend.exception.ErrorException;
import com.csf.whoami.converter.AdminDomain;

/**
 * The type User util.
 */
public final class AuthenticationUtils {

    /**
     * Gets current user.
     *
     * @return the current user
     */
    public static UserInfo getCurrentUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return null;
        }
        if (authentication.getPrincipal() instanceof UserInfo) {
            return (UserInfo) authentication.getPrincipal();
        }
        throw new CustomException(ErrorException.BAD_CREDENTIALS.getMessage(),
                ErrorException.BAD_CREDENTIALS.getCode(),
                HttpStatus.UNAUTHORIZED);
    }

    /**
     * Gets current user id.
     *
     * @return the current user id
     */
    public static String getCurrentUserId() {
        UserInfo user = getCurrentUser();
        return user == null ? null : user.getUserId();
    }

    /**
     * Gets current username.
     *
     * @return the current username
     */
    public static String getCurrentUsername() {
        UserInfo user = getCurrentUser();
        return user == null ? null : user.getUsername();
    }

    /**
     * Gets current password.
     *
     * @return the current password
     */
    public static String getCurrentPassword() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return (String) authentication.getCredentials();
        }
        throw new CustomException(ErrorException.BAD_CREDENTIALS.getMessage(),
                ErrorException.BAD_CREDENTIALS.getCode(),
                HttpStatus.UNAUTHORIZED);
    }

    public static AdminDomain getUser() {
        Optional<HttpServletRequest> request = getCurrentHttpRequest();
        if (request.isPresent()) {
            HttpSession session = request.get().getSession();
            return (AdminDomain) session.getAttribute("admin");
        }
        return null;
    }

    private static Optional<HttpServletRequest> getCurrentHttpRequest() {
        return Optional
                .ofNullable(RequestContextHolder.getRequestAttributes())
                .filter(ServletRequestAttributes.class::isInstance)
                .map(ServletRequestAttributes.class::cast)
                .map(ServletRequestAttributes::getRequest);
    }
}