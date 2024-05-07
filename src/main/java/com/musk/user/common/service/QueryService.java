package com.musk.user.common.service;

import java.util.List;
import java.util.Optional;

public interface QueryService<T> {
    List<T> findAll() ;
    boolean existsById(Long id);
}
