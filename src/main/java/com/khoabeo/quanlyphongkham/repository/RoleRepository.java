package com.khoabeo.quanlyphongkham.repository;

import com.khoabeo.quanlyphongkham.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
    Role findRoleByName(String name);
}
