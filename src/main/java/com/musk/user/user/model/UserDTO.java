package com.musk.user.user.model;

import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Component
@Data
@Builder
@ToString(exclude = {"id"})
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private String phone;
    private String job;
    private String regDate;
    private String modDate;
    private String token;
}
