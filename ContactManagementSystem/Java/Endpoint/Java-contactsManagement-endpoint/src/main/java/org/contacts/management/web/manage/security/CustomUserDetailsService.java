package org.contacts.management.web.manage.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if ("altaf".equals(username)) {
            // Return a UserDetails object with the user's credentials and roles
            return User.builder()
                    .username(username)
                    .password("password123") // You can use different password encoding mechanisms
                    .roles("ROLE_ADMIN")
                    .build();
        }

        throw new UsernameNotFoundException("User not found");
    }
}