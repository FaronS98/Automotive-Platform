package com.reply.Automotive_Platform.service;

import com.reply.Automotive_Platform.model.Offer;
import com.reply.Automotive_Platform.model.OfferRequest;
import com.reply.Automotive_Platform.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    @Override
    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    @Override
    public Page<Offer> findAllPageable(Pageable pageable) {
        return offerRepository.findAll(pageable);
    }

    @Override
    public Page<Offer> findOfferByPriceLessThanEqual(OfferRequest request, Pageable pageable) {
        return offerRepository.findByPriceLessThanEqual(pageable, request.priceLessThanEqual());
    }

    @Override
    public Offer addOffer(Offer offer) {
        return offerRepository.save(offer);
    }

    @Override
    public boolean deleteOfferById(Long id) {
        if (offerRepository.existsById(id)) {
            offerRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}