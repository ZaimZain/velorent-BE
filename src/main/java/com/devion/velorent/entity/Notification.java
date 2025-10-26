package com.devion.velorent.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long notificationId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    private String message;

    @Column(name = "is_read")
    private boolean isRead = false;

    @Column(name = "created_at", updatable = false, insertable = false)
    private LocalDateTime createdAt;
}
