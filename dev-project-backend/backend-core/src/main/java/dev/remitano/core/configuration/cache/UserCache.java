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

    private LoadingCache<String, User> userCacheLoader = CacheBuilder.newBuilder()
            .expireAfterWrite(30, TimeUnit.DAYS).build(new CacheLoader<String, User>() {
                @Override
                public User load(String email) {
                    return userRepository.findFirstByEmail(email);
                }
            });

    public User getUserByEmail(String email) {
        try {
            return userCacheLoader.get(email);
        } catch (Exception e) {
            _logger.error("Exception with message: " + e.getMessage());
        }
        return null;
    }
}
