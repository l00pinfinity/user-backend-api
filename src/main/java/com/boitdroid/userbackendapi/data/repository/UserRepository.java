package com.boitdroid.userbackendapi.data.repository;

import com.boitdroid.userbackendapi.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
