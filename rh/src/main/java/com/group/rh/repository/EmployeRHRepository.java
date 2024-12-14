
package com.group.rh.repository;

import com.group.rh.entity.EmployeRH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeRHRepository extends JpaRepository<EmployeRH, Integer> {
}
