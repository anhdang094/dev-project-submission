package dev.remitano.core.service;

import dev.remitano.infrastructure.dto.request.AuthenDto;
import dev.remitano.core.models.User;

/**
 * @author anhdx
 */
public interface UserService {

    /**
     * register service
     * @param dto
     * @return
     */
    User register(AuthenDto dto);

}
