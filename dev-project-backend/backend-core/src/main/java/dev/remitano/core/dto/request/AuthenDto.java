package dev.remitano.core.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenDto {

    private String email;

    private String password;
}
