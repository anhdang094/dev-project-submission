package dev.remitano.core.rest;

import dev.remitano.core.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api(value = "users", produces = APPLICATION_JSON_VALUE)
@RequestMapping("/api/users")
@CrossOrigin
public class UserController extends AbstractController {

}
