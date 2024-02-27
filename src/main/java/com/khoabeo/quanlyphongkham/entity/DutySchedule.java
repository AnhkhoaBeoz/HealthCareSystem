package com.khoabeo.quanlyphongkham.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_dutyschedule")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DutySchedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nurse_id")
    private Nurse nurse;

    @Column(name = "schedule_date")
    private LocalDateTime scheduleDate;

    @Column(name = "shift")
    private String shift;  // ca trực

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DutyScheduleStatus status;

    public enum DutyScheduleStatus {
        PENDING,
        IN_PROGRESS,
        COMPLETED,
        VERIFIED
    }
}
