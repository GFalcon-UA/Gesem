package ua.com.gfalcon.gesem.domain.auth;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 * @since 1.0.0
 */
public enum Role implements GrantedAuthority {
    ADMIN, USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
