package dev.remitano.core.rest;

import dev.remitano.core.dto.request.VideoUrlDto;
import dev.remitano.core.models.User;
import dev.remitano.core.service.VideoService;
import dev.remitano.infrastructure.enumeration.VoteType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/videos")
public class VideoController extends AbstractController {

    @Autowired
    private VideoService videoService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<?> getAllVideos(@RequestParam String page, @RequestParam String pageSize) {
        try {
            return new ResponseEntity<>(buildSuccessResponse(videoService.getAllVideo(
                    Integer.valueOf(page),
                    Integer.valueOf(pageSize))), HttpStatus.OK);
        } catch (Exception e) {
            _logger.error("Exception ", e);
            return new ResponseEntity<>(buildFailResponse(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<?> shareVideo(@RequestBody VideoUrlDto data) {
        try {
            return new ResponseEntity<>(buildSuccessResponse(videoService.shareVideo(data.getUrl())), HttpStatus.OK);
        } catch (Exception e) {
            _logger.error("Exception ", e);
            return new ResponseEntity<>(buildFailResponse(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/{id}/vote", method = RequestMethod.PUT)
    public ResponseEntity<?> voteVideo(@PathVariable String id, @RequestParam String type) {
        try {
            return new ResponseEntity<>(buildSuccessResponse(videoService.voteVideo(
                    Long.valueOf(id),
                    VoteType.fromValue(Integer.valueOf(type)))), HttpStatus.OK);
        } catch (Exception e) {
            _logger.error("Exception ", e);
            return new ResponseEntity<>(buildFailResponse(), HttpStatus.OK);
        }
    }

}
