package dev.remitano.core;

import dev.remitano.infrastructure.dto.response.YoutubeInfo;
import dev.remitano.infrastructure.utils.GsonUtils;
import dev.remitano.infrastructure.utils.HttpRequestUtils;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertNotNull;

/**
 * @author anhdx
 */
@SpringBootTest
public class DataTest {

    @Test
    public void testYoutubeApi1() throws Exception {
        YoutubeInfo youtubeInfo = GsonUtils.fromJsonString(HttpRequestUtils.sendGet(
                "http://www.youtube.com/oembed?url=https://www.youtube.com/watch?v=vgpGCN-7bEw&ab_channel=YahooFinance&format=json", 5000), YoutubeInfo.class);
        assertNotNull("html is null", youtubeInfo.getHtml());
        assertNotNull("title is null", youtubeInfo.getTitle());
        assertNotNull("author is null", youtubeInfo.getAuthorUrl());
        assertNotNull("provinder is null", youtubeInfo.getProviderUrl());
    }

    @Test
    public void testYoutubeApi2() throws Exception {
        YoutubeInfo youtubeInfo = GsonUtils.fromJsonString(HttpRequestUtils.sendGet(
                "http://www.youtube.com/oembed?url=https://www.youtube.com/watch?v=bP9gMpl1gyQ&ab_channel=TheSoulofWind&format=json", 5000), YoutubeInfo.class);
        assertNotNull("html is null", youtubeInfo.getHtml());
        assertNotNull("title is null", youtubeInfo.getTitle());
        assertNotNull("author is null", youtubeInfo.getAuthorUrl());
        assertNotNull("provinder is null", youtubeInfo.getProviderUrl());
    }

    @Test
    public void testYoutubeApi3() throws Exception {
        YoutubeInfo youtubeInfo = GsonUtils.fromJsonString(HttpRequestUtils.sendGet(
                "http://www.youtube.com/oembed?url=https://www.youtube.com/watch?v=XUC52X5MKQg&ab_channel=TidoKang&format=json", 5000), YoutubeInfo.class);
        assertNotNull("html is null", youtubeInfo.getHtml());
        assertNotNull("title is null", youtubeInfo.getTitle());
        assertNotNull("author is null", youtubeInfo.getAuthorUrl());
        assertNotNull("provinder is null", youtubeInfo.getProviderUrl());
    }

    @Test
    public void testYoutubeApi4() throws Exception {
        YoutubeInfo youtubeInfo = GsonUtils.fromJsonString(HttpRequestUtils.sendGet(
                "http://www.youtube.com/oembed?url=https://www.youtube.com/watch?v=fZI-HnKOHvg&ab_channel=ThanhT%E1%BB%8BnhThi%E1%BB%81n&format=json", 5000), YoutubeInfo.class);
        assertNotNull("html is null", youtubeInfo.getHtml());
        assertNotNull("title is null", youtubeInfo.getTitle());
        assertNotNull("author is null", youtubeInfo.getAuthorUrl());
        assertNotNull("provinder is null", youtubeInfo.getProviderUrl());
    }

    @Test
    public void testYoutubeApi5() throws Exception {
        YoutubeInfo youtubeInfo = GsonUtils.fromJsonString(HttpRequestUtils.sendGet(
                "http://www.youtube.com/oembed?url=https://www.youtube.com/watch?v=2iG-ZtB8RD4&ab_channel=BGMmaker&format=json", 5000), YoutubeInfo.class);
        assertNotNull("html is null", youtubeInfo.getHtml());
        assertNotNull("title is null", youtubeInfo.getTitle());
        assertNotNull("author is null", youtubeInfo.getAuthorUrl());
        assertNotNull("provinder is null", youtubeInfo.getProviderUrl());
    }

}