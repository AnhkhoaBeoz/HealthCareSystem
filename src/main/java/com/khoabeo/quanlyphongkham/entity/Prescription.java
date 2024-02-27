package com.khoabeo.quanlyphongkham.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tb_prescription")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createDateAt;
    @Column(nullable = false)
    private String diagnosis;
    @Column(nullable = false)
    private String totalCost;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account account;
    @OneToMany(mappedBy = "prescription")
    private Set<MedicinePrescription> medicinePrescriptions;
}
