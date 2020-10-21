package dev.remitano.infrastructure.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author anhdx
 */
public class DefaultResponse {

    private int code;

    private String message;

    private Integer duration = -1;

    @JsonProperty("auto_play")
    private Boolean autoPlay = false;

    @JsonProperty("ribbon_display")
    private Boolean ribbonDisplay = false;

    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Boolean getAutoPlay() {
        return autoPlay;
    }

    public void setAutoPlay(Boolean autoPlay) {
        this.autoPlay = autoPlay;
    }

    public Boolean getRibbonDisplay() {
        return ribbonDisplay;
    }

    public void setRibbonDisplay(Boolean ribbonDisplay) {
        this.ribbonDisplay = ribbonDisplay;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
