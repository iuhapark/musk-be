package com.musk.user.user.service;

import com.musk.user.common.component.Messenger;
import com.musk.user.common.component.security.JwtProvider;
import com.musk.user.user.model.UserDTO;
import com.musk.user.user.model.UserEntity;
import com.musk.user.user.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import lombok.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final JwtProvider jwtProvider;

    @Transactional
    @Override
    public Messenger save(UserDTO dto) {
        entityToDto((repository.save(dtoToEntity(dto))));
        return Messenger.builder()
                .message(repository.existsById(dto.getId()) ? "SUCCESS" : "FAILURE")
                .build();
    }

    @Transactional
    @Override
    public Messenger deleteById(Long id) {
        repository.deleteById(id);
        return Messenger.builder()
                .message(repository.findById(id).isPresent() ? "FAILURE" : "SUCCESS")
                .build();
    }

    @Override
    public List<UserDTO> findAll() {
        return repository.findAllByOrderByIdDesc().stream().map(i -> entityToDto(i)).toList();
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Messenger modify(UserDTO dto) {
        Optional<UserEntity> optionalUser = repository.findById(dto.getId());
        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();
            UserEntity modifyUser = user.toBuilder()
                    .password(dto.getPassword())
                    .job(dto.getJob())
                    .phone(dto.getPhone())
                    .email(dto.getEmail())
                    .build();
            Long updateUserId = repository.save(modifyUser).getId();

            return Messenger.builder()
                    .message("SUCCESS ID" + updateUserId)
                    .build();
        } else {
            return Messenger.builder()
                    .message("FAIL")
                    .build();
        }
    }

    @Transactional
    @Override
    public Messenger login(UserDTO dto) {
        log.info("Parameters received through login service" + dto);
        UserEntity user = repository.findByUsername(dto.getUsername()).get();
        String accessToken = jwtProvider.createToken(entityToDto(user));
        boolean flag = user.getPassword().equals(dto.getPassword());
        if (flag) {
            repository.modifyTokenById(user.getId(), accessToken);
        }
        jwtProvider.printPayload(accessToken);
        return Messenger.builder()
                .message(flag ? "SUCCESS" : "FAILURE")
                .accessToken(flag ? accessToken : "None")
                .build();
    }

    @Transactional
    @Override
    public Boolean logout(String accessToken) {
        Long id = jwtProvider.getPayload(accessToken.substring(7)).get("id",Long.class);
        repository.modifyTokenById(id, "");
        return repository.findById(id).get().getToken().isEmpty();
    }

    @Override
    public Boolean existsByUsername(String username) {
        Integer count = repository.existsByUsername(username);
        return count == 1;
    }
}
