package org.example.cpas.repository;

import org.example.cpas.entities.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {
}
