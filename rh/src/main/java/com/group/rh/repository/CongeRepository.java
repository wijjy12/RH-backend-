package com.group.rh.repository;

import com.group.rh.entity.Conge;
import com.group.rh.entity.LeaveStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface CongeRepository extends JpaRepository<Conge, Integer> {
    List<Conge> findByEmployeId_Id(int employeId);
    List<Conge> findByEmployeId_IdAndStatusAndFromDateBetween(int employeId, LeaveStatus status, LocalDate fromDate, LocalDate toDate);
    List<Conge> findByEmployeId_IdAndApprouveIsFalse(int employeId);
}
