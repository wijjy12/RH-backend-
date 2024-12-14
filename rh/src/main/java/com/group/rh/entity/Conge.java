package com.group.rh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Conge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "employe_id")
    private Employe employe;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "début_de_congé", nullable = false)
    private LocalDate fromDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fin_de_congé", nullable = false)
    private LocalDate toDate;
    @Column(name = "approuve")
    private boolean approuve;
    @Column( columnDefinition = "TEXT", nullable = false)
    private String raison;
    @Enumerated(EnumType.STRING)
    private LeaveStatus status;
}






