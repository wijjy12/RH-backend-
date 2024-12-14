package com.group.rh.repository;

import com.group.rh.entity.Admin;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository  // Indique que cette interface est un composant de persistance Spring
@Transactional   // Indique que les méthodes de cette interface doivent être exécutées dans une transaction
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findFirstBy();
}

