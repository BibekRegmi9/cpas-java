package org.example.cpas.repository;

import org.example.cpas.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository()
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
