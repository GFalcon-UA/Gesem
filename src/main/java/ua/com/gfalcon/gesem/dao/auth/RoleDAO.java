package ua.com.gfalcon.gesem.dao.auth;

import org.springframework.data.repository.CrudRepository;
import ua.com.gfalcon.gesem.domain.auth.Authority;
import ua.com.gfalcon.gesem.domain.auth.Role;

/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 * @since 1.0.0
 */
public interface RoleDAO extends CrudRepository<Role, Long> {
    Role findByAuthority(Authority authority);
}
