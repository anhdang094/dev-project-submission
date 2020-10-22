package dev.remitano.core.configuration.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import dev.remitano.core.models.User;
import dev.remitano.core.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class UserCache {

    private static final Logger _logger = LoggerFactory.getLogger(UserCache.class);

    @Autowired
    private UserRepository userRepository;

    private LoadingCache<Long, User> userCacheLoader = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.DAYS).build(new CacheLoader<Long, User>() {
                @Override
                public User load(Long id) {
                    return userRepository.findFirstById(id);
                }
            });

    public User getUserById(Long id) {
        try {
            return userCacheLoader.get(id);
        } catch (Exception e) {
            _logger.info("Exception with message: " + e.getMessage());
        }
        return null;
    }
}
