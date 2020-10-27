package dev.remitano.core.service.impl;

import dev.remitano.core.activemq.Producer;
import dev.remitano.core.configuration.cache.UserCache;
import dev.remitano.core.configuration.cache.VideoCache;
import dev.remitano.infrastructure.dto.request.SharingQueueDto;
import dev.remitano.infrastructure.dto.response.YoutubeInfo;
import dev.remitano.core.models.Video;
import dev.remitano.core.models.Vote;
import dev.remitano.core.repository.VideoRepository;
import dev.remitano.core.repository.VoteRepository;
import dev.remitano.core.service.VideoService;
import dev.remitano.infrastructure.enumeration.VoteType;
import dev.remitano.infrastructure.utils.GsonUtils;
import dev.remitano.infrastructure.utils.HttpRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    protected static Logger LOGGER = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private VideoCache videoCache;

    @Autowired
    private UserCache userCache;

    @Autowired
    private Producer producer;

    @Value("${app.youtube.url}")
    private String youtubeApi;

    @Value("${app.activemq.queue-name}")
    private String videoQueue;

    @Override
    public Page<Video> getAllVideo(int page, int pageSize) {
        return videoCache.getVideo(page, pageSize);
    }

    @Override
    public void pushShareVideo(String url) {
        SharingQueueDto sharingQueueDto = new SharingQueueDto();
        sharingQueueDto.setUrl(url);
        sharingQueueDto.setUserName(SecurityContextHolder.getContext().getAuthentication().getName());
        producer.sendMessage(videoQueue, GsonUtils.toJsonString(sharingQueueDto));
    }

    @Override
    public Video shareVideo(SharingQueueDto dto) {
        try {
            YoutubeInfo youtubeInfo = GsonUtils.fromJsonString(HttpRequestUtils.sendGet(youtubeApi.replace("{videoUrl}", dto.getUrl()), 5000), YoutubeInfo.class);
            Video video = new Video();
            video.setShareBy(dto.getUserName());
            video.setTitle(youtubeInfo.getTitle());
            video.setLink(dto.getUrl());
            video.setVoteDown(0L);
            video.setVoteUp(0L);
            video.setDescriptions(youtubeInfo.getTitle());
            video.setAuthor(youtubeInfo.getAuthorName());
            video.setAuthorUrl(youtubeInfo.getAuthorUrl());
            video.setHtml(youtubeInfo.getHtml());
            video.setCreatedDate(LocalDateTime.now());
            Video result = videoRepository.save(video);
            videoCache.deleteVideoCache();
            return result;
        } catch (IOException e) {
            LOGGER.error("Exception: ", e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Video voteVideo(Long videoId, VoteType type) {
        Video video = videoRepository.getOne(videoId);
        if (video == null) {
            return null;
        }
        Vote vote = new Vote();
        vote.setUserId(userCache.getUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getId());
        vote.setVideoId(videoId);
        vote.setType(type.getCode());
        vote.setCreatedDate(LocalDateTime.now());
        voteRepository.save(vote);
        switch (type) {
            case UP:
                Long upVote = video.getVoteUp();
                video.setVoteUp(upVote + 1);
                break;
            case DOWN:
                Long downVote = video.getVoteDown();
                video.setVoteDown(downVote + 1);
                break;
            default:
                break;
        }
        Video result = videoRepository.save(video);
        videoCache.deleteVideoCache();
        return result;
    }
}
