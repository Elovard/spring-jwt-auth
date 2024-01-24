package com.dydzik.jwtauthboot3security6.controller;

import com.dydzik.jwtauthboot3security6.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class DemoController {
    private final UserService service;

    @GetMapping
    @Operation(summary = "Available only for authorized users")
    public String demo() {
        return "Hello, World!";
    }

    @GetMapping("/admin")
    @Operation(summary = "Available only for users with Admin role")
    public String demoAdmin() {
        return "Hello, Admin!";
    }

    @GetMapping("/get-admin")
    @Operation(summary = "Get admin role (for demo only)")
    public void getAdmin() {
        service.getAdmin();
    }
}
