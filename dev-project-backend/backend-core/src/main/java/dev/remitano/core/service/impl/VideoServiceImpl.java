package dev.remitano.core.service.impl;

import dev.remitano.core.dto.request.AuthenDto;
import dev.remitano.core.models.User;
import dev.remitano.core.models.Video;
import dev.remitano.core.repository.UserRepository;
import dev.remitano.core.repository.VideoRepository;
import dev.remitano.core.service.UserService;
import dev.remitano.core.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    protected static Logger _logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public Page<Video> getAllVideo(int page, int pageSize) {
        return videoRepository.findAll(PageRequest.of(page - 1, pageSize));
    }
}
