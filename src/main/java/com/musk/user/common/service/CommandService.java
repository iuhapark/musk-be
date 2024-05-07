package com.musk.user.common.service;

import com.musk.user.common.component.Messenger;

public interface CommandService<T> {
    Messenger save(T t);
    Messenger deleteById(Long id);
    Messenger modify(T t);
}
