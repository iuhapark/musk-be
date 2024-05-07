package com.musk.user.user.repository;

import com.musk.user.user.model.UserDTO;

import java.util.List;

public interface UserDAO {
    List<UserDTO> getAllUsers();

}
