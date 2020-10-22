package dev.remitano.core.rest;

import dev.remitano.core.dto.request.AuthenDto;
import dev.remitano.core.service.AuthenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authen")
@CrossOrigin
public class AuthenController extends AbstractController {

    @Autowired
    private AuthenService authenService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody AuthenDto dto) {
        try {
            return new ResponseEntity<>(buildSuccessResponse(authenService.register(dto)), HttpStatus.OK);
        } catch (Exception e) {
            _logger.error("Exception ", e);
            return new ResponseEntity<>(buildFailResponse(), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody AuthenDto dto) {
        try {
            return new ResponseEntity<>(buildSuccessResponse(authenService.login(dto)), HttpStatus.OK);
        } catch (Exception e) {
            _logger.error("Exception ", e);
            return new ResponseEntity<>(buildFailResponse(), HttpStatus.OK);
        }
    }

}
