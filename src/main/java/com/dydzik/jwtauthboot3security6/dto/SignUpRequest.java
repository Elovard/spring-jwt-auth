package com.dydzik.jwtauthboot3security6.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "SignUp request")
public class SignUpRequest {

    @Schema(description = "Username", example = "John")
    @Size(min = 5, max = 50, message = "Username should contain 5 - 50 chars")
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @Schema(description = "Email", example = "john@example.com")
    @Size(min = 5, max = 255, message = "Email should contain 5 - 255 chars")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should have john@example.com format")
    private String email;

    @Schema(description = "Password")
    @Size(max = 255, message = "Password length should contain less than 255 chars")
    @NotBlank(message = "Password cannot be blank")
    private String password;

}
