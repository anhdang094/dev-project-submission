package dev.remitano.core.service.impl;

import dev.remitano.core.dto.request.AuthenDto;
import dev.remitano.core.repository.UserRepository;
import dev.remitano.core.service.AuthenService;
import dev.remitano.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthenServiceImpl implements AuthenService {

    protected static Logger _logger = LoggerFactory.getLogger(AuthenServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public AuthenDto register(AuthenDto dto) {
        return null;
    }

    @Override
    public AuthenDto login(AuthenDto dto) {
        return null;
    }
}
