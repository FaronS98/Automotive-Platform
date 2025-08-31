package com.reply.Automotive_Platform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer {

    @Id @GeneratedValue private Long id;

    private String title;

    private BigDecimal price;

    private Boolean available;

    @ManyToOne
    private Car car;
}
