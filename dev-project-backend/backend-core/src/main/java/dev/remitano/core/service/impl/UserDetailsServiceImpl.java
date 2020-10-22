package dev.remitano.core.service;

import dev.remitano.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        dev.remitano.core.models.User user = userRepository.findFirstByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(user.getEmail(), user.getPassword(), emptyList());

    }

}

