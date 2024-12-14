package com.group.rh.repository;

import com.group.rh.entity.OurUsers;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Transactional
@Repository
public interface UsersRepo extends JpaRepository<OurUsers,Integer> {

    Optional<OurUsers> findByEmail(@Param("email") String email);
}