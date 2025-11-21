package com.county.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "counties")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class County {
    @Id
    @Column(length = 10)
    private String fips;

    @Column(length = 10, nullable = false)
    private String state;

    @Column(length = 255, nullable = false)
    private String name;
}