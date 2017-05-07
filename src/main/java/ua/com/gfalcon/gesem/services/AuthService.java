package ua.com.gfalcon.gesem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.gfalcon.gesem.dao.auth.RoleDAO;
import ua.com.gfalcon.gesem.dao.auth.UserDAO;
import ua.com.gfalcon.gesem.domain.auth.Authority;
import ua.com.gfalcon.gesem.domain.auth.Role;
import ua.com.gfalcon.gesem.domain.auth.User;
import ua.com.gfalcon.gesem.exeptions.AuthorizationException;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Environment environment;

    private String adminUsername;
    private String adminPassword;

    @PostConstruct
    private void init() {
        List<User> users;
        adminUsername = environment.getProperty("database.admin.username");
        adminPassword = environment.getProperty("database.admin.password");
        try {
            try {
                users = (List<User>) userDAO.findAll();
                if (users.isEmpty()) {
                    activateMainAdmin();
                } else {
                    boolean isFound = false;
                    for (User user : users) {
                        if (user.getUsername().equals(adminUsername)) {
                            if (!user.isEnabled()) {
                                user.setEnabled(true);
                                if (!user.getAuthorities().contains(Authority.ADMIN)) {
                                    Role role = initAdminsRole();
                                    user.addRole(role);
                                }
                                userDAO.save(user);
                            }
                            isFound = true;
                            break;
                        }
                    }
                    if (!isFound) {
                        activateMainAdmin();
                    }
                }
            } catch (NullPointerException e) {
                activateMainAdmin();
            }
            initRoles();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private Role initAdminsRole() {
        Role role = roleDAO.findByAuthority(Authority.ADMIN);
        if (role == null) {
            role = new Role(Authority.ADMIN);
            roleDAO.save(role);
        }
        return role;
    }

    private void activateMainAdmin() throws AuthorizationException {
        User admin = null;
        try {
            admin = userDAO.findByUsername(adminUsername);
        } finally {
            if (admin == null) {

                User user = new User(adminUsername, passwordEncoder.encode(adminPassword), initAdminsRole(), true);
                try {
                    userDAO.save(user);
                } catch (Exception e) {
                    throw new AuthorizationException(e.getMessage());
                }
            }
        }
    }

    private void initRoles() {
        List<Role> roles = (List<Role>) roleDAO.findAll();
        for (Authority name : Authority.values()) {
            Role role = new Role(name);
            if (!roles.contains(role)) {
                roleDAO.save(role);
            }
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDAO.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User [username=%s] not found", username));
        }
        return user;
    }
}
