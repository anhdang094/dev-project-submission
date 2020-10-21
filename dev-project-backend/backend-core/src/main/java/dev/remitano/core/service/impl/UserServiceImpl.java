package dev.remitano.core.service.impl;

import dev.remitano.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    protected static Logger _logger = LoggerFactory.getLogger(UserServiceImpl.class);

}
