package dev.remitano.core.rest;

import dev.remitano.core.dto.request.AuthenDto;
import dev.remitano.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController extends AbstractController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<?> register(@RequestBody AuthenDto dto) {
        try {
            return new ResponseEntity<>(buildSuccessResponse(userService.register(dto)), HttpStatus.OK);
        } catch (Exception e) {
            _logger.error("Exception ", e);
            return new ResponseEntity<>(buildFailResponse(), HttpStatus.OK);
        }
    }

}
