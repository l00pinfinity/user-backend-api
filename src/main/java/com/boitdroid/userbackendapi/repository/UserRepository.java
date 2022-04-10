package com.boitdroid.userbackendapi.repository;

import com.boitdroid.userbackendapi.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
}
