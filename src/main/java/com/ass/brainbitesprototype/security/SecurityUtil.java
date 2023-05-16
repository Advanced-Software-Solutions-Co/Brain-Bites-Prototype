package com.ass.brainbitesprototype.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {

    // Get username of the current user if they are authenticated.
    public static String getSessionUser() {
        // Authentication status of the user (get info from cookies).
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            return currentUsername;
        }

        // User is not authenticated i.e. an anonymous session user.
        return null;
    }
}
