    package com.khoabeo.quanlyphongkham.entity;

    import com.khoabeo.quanlyphongkham.Constants.Status;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;
    import org.hibernate.annotations.CreationTimestamp;
    import org.hibernate.annotations.UpdateTimestamp;

    import java.time.LocalDateTime;

    @Entity
    @Table(name = "tb_appointment")
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class Appointment {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private long id;

        @CreationTimestamp
        private LocalDateTime createDateAt;

        @UpdateTimestamp
        private LocalDateTime updateDateAt;

        @Enumerated(EnumType.ORDINAL)
        private Status status;
        @OneToOne
        @JoinColumn(name = "doctor_id")
        private Doctor doctor;

        @OneToOne
        @JoinColumn(name = "patient_id")
        private Patient account;

        public Appointment(Status status, Doctor doctor, Patient account) {
            this.status = status;
            this.doctor = doctor;
            this.account = account;
        }
    }
