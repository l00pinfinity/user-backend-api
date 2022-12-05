package com.boitdroid.userbackendapi.repository;

import com.boitdroid.userbackendapi.data.models.RoleNames;
import com.boitdroid.userbackendapi.data.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {
    Optional<Roles> findByName(RoleNames roleName);
}
