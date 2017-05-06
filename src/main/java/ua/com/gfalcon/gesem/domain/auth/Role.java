package ua.com.gfalcon.gesem.domain.auth;

import ua.com.gfalcon.entitydao.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity
public class Role extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false)
    private Authority authority;

    public Role(Authority authority) {
        setAuthority(authority);
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
