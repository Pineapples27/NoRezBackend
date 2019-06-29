package za.co.norezgaming.backend.service.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import za.co.norezgaming.backend.service.security.authority.Authority;

import java.util.Optional;
import java.util.UUID;

@Service
public class SecurityService {
    public UUID getAuthentication(){
        return UUID.randomUUID();
    }

    public Optional<UUID> getAccountUUID() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Claims claims = (Claims) authentication.getDetails();
            if (claims.get("UUID") != null) {
                return Optional.ofNullable(UUID.fromString((String) claims.get("UUID")));
            }
            return Optional.empty();
        } catch (Exception e) {
            return Optional.empty();//If there is no security context when the user is not logged in the you cannot get the context
        }
    }

    public boolean isElevatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals(Authority.ELEVATED_USER.getValue())) {
                return true;
            }
        }
        return false;
    }

    public boolean isStandardUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals(Authority.STANDARD_USER.getValue())) {
                return true;
            }
        }
        return false;
    }
}
