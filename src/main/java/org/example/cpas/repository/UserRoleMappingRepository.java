package org.example.cpas.repository;

import org.example.cpas.entities.UserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleMappingRepository extends JpaRepository<UserRoleMapping, Integer> {
}
