package dev.remitano.core.configuration.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import dev.remitano.core.models.User;
import dev.remitano.core.models.Video;
import dev.remitano.core.repository.UserRepository;
import dev.remitano.core.repository.VideoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class VideoCache {

    private static final Logger _logger = LoggerFactory.getLogger(VideoCache.class);

    private static final String KEY = "VIDEO|KEY";

    @Autowired
    private VideoRepository videoRepository;

    private LoadingCache<String, Page<Video>> videoCacheLoader = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.DAYS).build(new CacheLoader<String, Page<Video>>() {
                @Override
                public Page<Video> load(String key) {
                    return videoRepository.findAll(PageRequest.of(0, 5, Sort.by("createdDate").descending()));
                }
            });

    public Page<Video> getVideo(int page, int pageSize) {
        try {
            // we cache first page for optimize perfomance
            if (page == 1) {
                return videoCacheLoader.get(KEY);
            } else {
                return videoRepository.findAll(PageRequest.of(page - 1, pageSize, Sort.by("createdDate").descending()));
            }
        } catch (Exception e) {
            _logger.error("Exception with message: " + e.getMessage());
        }
        return null;
    }

    public void deleteVideoCache() {
        videoCacheLoader.invalidate(KEY);
    }
}
