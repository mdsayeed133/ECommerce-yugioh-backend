package com.ecommerce.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "country")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "country_code",nullable = false, unique = true)
    private String countryCode ;
}
