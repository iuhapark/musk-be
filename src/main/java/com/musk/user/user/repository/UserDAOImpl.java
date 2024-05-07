package com.musk.user.user.repository;

import com.musk.user.user.model.UserDTO;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserDAOImpl implements UserDAO{

    @Override
    public List<UserDTO> getAllUsers() {
        return null;
    }
}
