package com.reply.Automotive_Platform.repository;

import com.reply.Automotive_Platform.model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface OfferRepository extends JpaRepository<Offer, Long> {

    Page<Offer> findByPriceLessThanEqual(Pageable pageable, BigDecimal price);
}
