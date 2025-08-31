package com.reply.Automotive_Platform.service;

import com.reply.Automotive_Platform.model.Offer;
import com.reply.Automotive_Platform.model.OfferRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OfferService {

    List<Offer> findAll();

    Page<Offer> findAllPageable(Pageable pageable);

    Page<Offer> findOfferByPriceLessThanEqual(OfferRequest request, Pageable pageable);

    Offer addOffer(Offer offer);

    boolean deleteOfferById(Long id);
}
