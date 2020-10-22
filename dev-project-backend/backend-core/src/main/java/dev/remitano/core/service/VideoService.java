package dev.remitano.core.service;

import dev.remitano.core.dto.request.AuthenDto;
import dev.remitano.core.models.User;
import dev.remitano.core.models.Video;
import org.springframework.data.domain.Page;

/**
 * @author anhdx
 */
public interface VideoService {

    /**
     * get all video
     * @return
     */
    Page<Video> getAllVideo(int page, int pageSize);

}
