package dev.remitano.core.rest;

import dev.remitano.core.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin
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

}
