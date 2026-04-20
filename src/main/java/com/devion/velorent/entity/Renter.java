//package com.devion.velorent.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@AllArgsConstructor
//@NoArgsConstructor
//@Getter
//@Setter
//@Entity
//@Table(name = "renters")
//public class Renter {
//
//    @Id
//    @Column(name = "renter_id")
//    private Long renterId;
//
//    @OneToOne
//    @MapsId
//    @JoinColumn(name = "renter_id")
//    private AppUser user;
//
//    @Column(name = "company_name")
//    private String companyName;
//
//    @Column(name = "ic_number")
//    private String icNumber;
//}
