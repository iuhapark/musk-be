package com.musk.user.user.controller;

import com.musk.user.common.component.Messenger;
import com.musk.user.user.model.UserDTO;
import com.musk.user.user.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/users")
@Slf4j
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "Invalid ID supplied"),
        @ApiResponse(responseCode = "404", description = "Customer not found")
})
public class UserController {
    private final UserService service;


    @SuppressWarnings("static-access")
    @PostMapping(path = "/save")
    public ResponseEntity<Messenger> save(@RequestBody UserDTO dto) throws SQLException {
        return ResponseEntity.ok(service.save(dto));
    }


    @PutMapping("/modify")
    public ResponseEntity<Messenger> modify(@RequestBody UserDTO param) {
        return ResponseEntity.ok(service.modify(param));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Messenger> deleteById(@RequestParam Long id) {
        return ResponseEntity.ok(service.deleteById(id));
    }


    @GetMapping("/exists")
    public ResponseEntity<Boolean> existsById(@RequestParam Long id) {
        return ResponseEntity.ok(service.existsById(id));
    }

    @GetMapping("/logout")
    public ResponseEntity<Boolean> logout(@RequestHeader("authorization") String accessToken){
        var flag = service.logout(accessToken);
        return ResponseEntity.ok(flag);
    }

}
