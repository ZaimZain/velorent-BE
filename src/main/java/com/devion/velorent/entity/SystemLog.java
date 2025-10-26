package com.devion.velorent.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "system_logs")
public class SystemLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long logId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    private String action;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "log_time", updatable = false, insertable = false)
    private LocalDateTime logTime;
}
