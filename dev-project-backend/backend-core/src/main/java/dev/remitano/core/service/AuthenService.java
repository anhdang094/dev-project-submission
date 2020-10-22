package dev.remitano.core.service;

import dev.remitano.core.dto.request.AuthenDto;

/**
 * @author anhdx
 */
public interface AuthenService {

    /**
     * register service
     * @param dto
     * @return
     */
    AuthenDto register(AuthenDto dto);

    /**
     * login service
     * @param dto
     * @return
     */
    AuthenDto login(AuthenDto dto);

}
