package dev.remitano.infrastructure.dto.response;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class YoutubeInfo {

    @SerializedName("html")
    private String html;

    @SerializedName("provider_url")
    private String providerUrl;

    @SerializedName("version")
    private String version;

    @SerializedName("title")
    private String title;

    @SerializedName("author_url")
    private String authorUrl;

    @SerializedName("author_name")
    private String authorName;

}
