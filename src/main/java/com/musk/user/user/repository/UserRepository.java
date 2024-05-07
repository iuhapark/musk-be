package com.musk.user.user.repository;

import com.musk.user.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAllByOrderByIdDesc();

    Optional<UserEntity> findByUsername(String username);

    @Modifying
    @Query("update users a set a.token = :token where a.id = :id")
    void modifyTokenById(@Param("id") Long id, @Param("token") String token);


    @Query("select count(id) as count from users where username =:username")
    Integer existsByUsername(@Param("username") String username);
}
