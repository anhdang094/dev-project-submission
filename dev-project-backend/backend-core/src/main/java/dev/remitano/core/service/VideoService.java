package dev.remitano.core.service;

import dev.remitano.core.models.Video;
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
     * share video
     * @param url
     * @return
     */
    Video shareVideo(String url);

    /**
     * vote video
     * @param type
     * @return
     */
    Video voteVideo(Long videoId, VoteType type);

}
