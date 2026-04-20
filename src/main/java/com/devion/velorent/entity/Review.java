//package com.devion.velorent.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//import java.time.LocalDateTime;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Entity
//@Table(name = "reviews")
//public class Review {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "review_id")
//    private Long reviewId;
//
//    @ManyToOne
//    @JoinColumn(name = "rental_id")
//    private Rental rental;
//
//    private int rating;
//
//    @Column(columnDefinition = "TEXT")
//    private String comment;
//
//    @Column(name = "created_at", updatable = false, insertable = false)
//    private LocalDateTime createdAt;
//}
