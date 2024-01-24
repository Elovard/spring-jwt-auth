package com.dydzik.jwtauthboot3security6.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "SignIn request")
public class SignInRequest {

    @Schema(description = "Username", example = "John")
    @Size(min = 5, max = 50, message = "Username should contain 5 - 50 chars")
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @Schema(description = "Password")
    @Size(max = 255, message = "Password length should contain less than 255 chars")
    @NotBlank(message = "Password cannot be blank")
    private String password;
}
