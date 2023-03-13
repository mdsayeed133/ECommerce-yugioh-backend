package com.ecommerce.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "card_type")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class CardType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Integer typeId;

    @Column(name = "type_name",nullable = false, unique = true)
    private String typeName ;
}
