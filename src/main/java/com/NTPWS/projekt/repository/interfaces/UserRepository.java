package com.NTPWS.projekt.repository.interfaces;

import com.NTPWS.projekt.model.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findByGroups_Id(Long GroupId);
}
