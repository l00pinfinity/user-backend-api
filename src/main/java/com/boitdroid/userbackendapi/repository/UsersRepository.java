package com.boitdroid.userbackendapi.repository;

import com.boitdroid.userbackendapi.data.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UsersRepository extends JpaRepository<Users,Long> {

    Optional<Users> findByEmail(String email);

    Optional<Users> findByResetToken(String resetToken);

    Optional<Users> findByUsername(String username);

    Optional<Users> findByEmailOrUsername(String username, String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
