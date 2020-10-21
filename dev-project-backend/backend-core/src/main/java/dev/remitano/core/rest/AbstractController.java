package dev.remitano.core.rest;

import dev.remitano.infrastructure.enumeration.ErrorMessage;
import dev.remitano.infrastructure.response.DefaultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractController {

    protected static Logger _logger = LoggerFactory.getLogger(AbstractController.class);

    protected DefaultResponse buildSuccessResponse(Object data) {
        DefaultResponse response = new DefaultResponse();
        response.setCode(ErrorMessage.SUCCESS.getCode());
        response.setMessage(ErrorMessage.SUCCESS.getMessage());
        response.setData(data);
        return response;
    }

    protected DefaultResponse buildInvalidResponse() {
        DefaultResponse response = new DefaultResponse();
        response.setCode(ErrorMessage.INVALID_PARAM.getCode());
        response.setMessage(ErrorMessage.INVALID_PARAM.getMessage());
        return response;
    }

    protected DefaultResponse buildAccessFailResponse() {
        DefaultResponse response = new DefaultResponse();
        response.setCode(ErrorMessage.ACCESS_FAIL.getCode());
        response.setMessage(ErrorMessage.ACCESS_FAIL.getMessage());
        return response;
    }

    protected DefaultResponse buildFailResponse() {
        DefaultResponse response = new DefaultResponse();
        response.setCode(ErrorMessage.FAIL.getCode());
        response.setMessage(ErrorMessage.FAIL.getMessage());
        return response;
    }

    protected DefaultResponse buildNoContentResponse() {
        DefaultResponse response = new DefaultResponse();
        response.setCode(ErrorMessage.SUCCESS.getCode());
        response.setMessage(ErrorMessage.SUCCESS.getMessage());
        response.setData(null);
        return response;
    }

}
