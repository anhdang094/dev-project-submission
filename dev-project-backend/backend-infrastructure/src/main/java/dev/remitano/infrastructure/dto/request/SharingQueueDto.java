package dev.remitano.infrastructure.dto.request;


import java.io.Serializable;
import java.util.Objects;

public class SharingQueueDto implements Serializable {

    protected static final long serialVersionUID = 8947981683527355664L;

    private String url;

    private String userName;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SharingQueueDto that = (SharingQueueDto) o;
        return Objects.equals(url, that.url) &&
                Objects.equals(userName, that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, userName);
    }

}
