package vn.smart.web.domain.activemq.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import vn.smart.web.domain.model.TransactionLog;
import vn.smart.web.infrastructure.utils.GsonUtils;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import static vn.smart.web.infrastructure.constants.CommonConstants.TRANS_QUEUE_NAME;

/**
 * @author anhdx
 */
@Component
@Transactional
public class TransactionListener {

    protected static Logger _logger = LoggerFactory.getLogger(TransactionListener.class);

    @JmsListener(destination = TRANS_QUEUE_NAME)
    public void receiveMessage(Message jsonMessage) throws JMSException {
        if (jsonMessage instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) jsonMessage;
            TransactionLog result = GsonUtils.fromJsonString(textMessage.getText(), TransactionLog.class);
            _logger.info("start get queue with data " + textMessage.getText());
        }
    }
}
