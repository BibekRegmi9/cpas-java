package org.example.cpas.repository;

import org.example.cpas.entities.ModulePrivilegeMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModulePrivilegeMappingRepository extends JpaRepository<ModulePrivilegeMapping, Integer> {
}
