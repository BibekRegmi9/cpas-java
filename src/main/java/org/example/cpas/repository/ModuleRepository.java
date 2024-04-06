package org.example.cpas.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.example.cpas.entities.Module;

public interface ModuleRepository extends JpaRepository<Module, Integer> {
}
