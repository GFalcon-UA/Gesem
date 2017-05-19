package ua.com.gfalcon.gesem.domain.auth;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import ua.com.gfalcon.entitydao.AbstractEntity;

import javax.persistence.*;

/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 * @since 1.0.0
 */
@Entity(name = "Role")
@Table(name = "ROLES")
public class Role extends AbstractEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "AUTHORITY", unique = true, nullable = false)
    private Authority authority;

    protected Role() {

    }

    public Role(Authority authority) {
        setAuthority(authority);
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Role)) return false;

        Role role = (Role) o;

        return new EqualsBuilder()
                .append(authority, role.authority)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(authority)
                .toHashCode();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Role{");
        sb.append("authority=").append(authority);
        sb.append('}');
        return sb.toString();
    }
}
