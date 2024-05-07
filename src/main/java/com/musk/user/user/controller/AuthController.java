package com.musk.user.user.controller;

import com.musk.user.common.component.Messenger;
import com.musk.user.user.model.UserDTO;
import com.musk.user.user.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
})
@CrossOrigin(origins = "*", allowedHeaders = "*") // http://localhost:3000 에서 온 AJAX요청만 받아주겠다는 의미.
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/auth")
@Log4j2

public class AuthController {
    private final UserService service;

    @PostMapping(path = "/login")
    public ResponseEntity<Messenger> login(@RequestBody UserDTO dto) throws SQLException {
        Messenger messenger = service.login(dto);
        return ResponseEntity.ok(messenger);
    }
}
