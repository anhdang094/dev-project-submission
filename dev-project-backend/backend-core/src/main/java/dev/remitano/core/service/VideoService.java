package dev.remitano.core.service;

import dev.remitano.core.models.Video;
import dev.remitano.infrastructure.dto.request.SharingQueueDto;
import dev.remitano.infrastructure.enumeration.VoteType;
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

    /**
     * push to queue to handle async share video
     * @param url
     */
    void pushShareVideo(String url);

    /**
     * shareVideo
     * @param dto
     * @return
     */
    Video shareVideo(SharingQueueDto dto);

    /**
     * vote video
     * @param type
     * @return
     */
    Video voteVideo(Long videoId, VoteType type);

}
