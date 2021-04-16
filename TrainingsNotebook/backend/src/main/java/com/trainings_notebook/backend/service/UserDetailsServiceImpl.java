package com.trainings_notebook.backend.service;

import com.trainings_notebook.backend.domain.User;
import com.trainings_notebook.backend.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl  implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(name);
        User user = userOptional
                .orElseThrow(() -> new UsernameNotFoundException("No user found with username: " + name));

        return new org.springframework.security
                .core.userdetails.User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true,true, getAuthorities("USER"));
    }
    private Collection<? extends GrantedAuthority> getAuthorities(String role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role));
    }

}
