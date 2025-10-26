package com.devion.velorent.entity;

import com.devion.velorent.enums.IdentificationType;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @Column(name = "customer_id")
    private Long customerId;

    @OneToOne
    @MapsId
    @JoinColumn(name = "customer_id")
    private AppUser user;

    @Column(name = "identification_number")
    private String identificationNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "identification_type")
    private IdentificationType identificationType;

    @Column(name = "license_number")
    private String licenseNumber;
}
