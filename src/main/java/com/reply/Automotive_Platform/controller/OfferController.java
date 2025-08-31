package com.reply.Automotive_Platform.controller;

import com.reply.Automotive_Platform.model.Offer;
import com.reply.Automotive_Platform.model.OfferRequest;
import com.reply.Automotive_Platform.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class OfferController {

    private final OfferService offerService;

    @GetMapping("public/offers")
    public ResponseEntity<List<Offer>> findAll() {
        return ResponseEntity.ok(offerService.findAll());
    }

    @GetMapping("public/offers/all")
    public ResponseEntity<Page<Offer>> findAllPageable(Pageable pageable) {
        return ResponseEntity.ok(offerService.findAllPageable(pageable));
    }

    @PostMapping("public/offers")
    public ResponseEntity<Page<Offer>> findOfferByPriceLessThanEqual(@RequestBody OfferRequest request, Pageable pageable) {
        return ResponseEntity.ok(offerService.findOfferByPriceLessThanEqual(request, pageable));
    }

    @PostMapping("auth/offers/add")
    public ResponseEntity<Offer> addOffer(@RequestBody Offer offer) {
        Offer savedOffer = offerService.addOffer(offer);
        return ResponseEntity.ok(savedOffer);
    }


    @DeleteMapping("auth/offers/{id}")
    public ResponseEntity<String> deleteOffer(@PathVariable Long id) {
        boolean deleted = offerService.deleteOfferById(id);
        if (deleted) {
            return ResponseEntity.ok("Oferta została pomyślnie usunięta.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nie znaleziono oferty o ID " + id + ".");
        }
    }
}
