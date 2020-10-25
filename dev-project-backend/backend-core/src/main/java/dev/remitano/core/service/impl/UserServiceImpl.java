package dev.remitano.core.service.impl;

import dev.remitano.core.dto.request.AuthenDto;
import dev.remitano.core.models.User;
import dev.remitano.core.repository.UserRepository;
import dev.remitano.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    protected static Logger _logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User register(AuthenDto dto) {
        User user = userRepository.findFirstByEmail(dto.getEmail());
        if (user == null) {
            User userLogin = new User();
            userLogin.setEmail(dto.getEmail());
            userLogin.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
            userLogin.setCreatedDate(LocalDateTime.now());
            return userRepository.save(userLogin);
        } else {
            return user;
        }
    }
}
