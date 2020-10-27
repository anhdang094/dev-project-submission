package dev.remitano.core.activemq.listener;

import dev.remitano.core.service.VideoService;
import dev.remitano.infrastructure.dto.request.SharingQueueDto;
import dev.remitano.infrastructure.utils.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

/**
 * @author anhdx
 */
@Component
@Transactional
public class VideoShareListener {

    protected static Logger _logger = LoggerFactory.getLogger(VideoShareListener.class);

    @Autowired
    private VideoService videoService;

    @JmsListener(destination = "${app.activemq.queue-name}")
    public void receiveMessage(Message jsonMessage) throws JMSException {
        if (jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) jsonMessage;
            videoService.shareVideo(GsonUtils.fromJsonString(textMessage.getText(), SharingQueueDto.class));
        }
    }
}
