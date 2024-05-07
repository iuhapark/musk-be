package com.musk.user.user.service;

import com.musk.user.common.component.Messenger;
import com.musk.user.common.service.CommandService;
import com.musk.user.common.service.QueryService;
import com.musk.user.user.model.UserDTO;
import com.musk.user.user.model.UserEntity;

public interface UserService extends CommandService<UserDTO>, QueryService<UserDTO> {
    Messenger modify(UserDTO user);

    default UserEntity dtoToEntity(UserDTO dto) {
        return UserEntity.builder()
                .id(dto.getId())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .name(dto.getName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .job(dto.getJob())
                .build();
    }

    default UserDTO entityToDto(UserEntity user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .name(user.getName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .job(user.getJob())
                .build();
    }

    Messenger login(UserDTO param);

    Boolean logout(String accessToken);

    Boolean existsByUsername(String username);
}
