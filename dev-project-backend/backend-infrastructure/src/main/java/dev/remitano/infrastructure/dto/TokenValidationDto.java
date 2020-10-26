package dev.remitano.infrastructure.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenValidationDto {

    private Long id;

    private String artistName;

    private Long profileImageId;

    private String metamaskAddress;

    private String email;

    private String token;
}
