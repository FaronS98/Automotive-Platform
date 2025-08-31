package com.reply.Automotive_Platform;

import com.reply.Automotive_Platform.model.Car;
import com.reply.Automotive_Platform.model.Offer;
import com.reply.Automotive_Platform.repository.CarRepository;
import com.reply.Automotive_Platform.repository.OfferRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;

@SpringBootApplication
public class AutomotivePlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutomotivePlatformApplication.class, args);
    }

    @Bean
    CommandLineRunner init(OfferRepository offerRepository, CarRepository carRepository) {
        return args -> {
            Car car1 =
                    carRepository.save(Car.builder().brand("Audi").model("S5").yearProduction(2019).build());
            Car car2 =
                    carRepository.save(Car.builder().brand("BMW").model("Seria 5").yearProduction(2017).build());

            offerRepository.saveAll(
                    List.of(
                            Offer.builder()
                                    .title(car1.getBrand() + car1.getModel())
                                    .price(new BigDecimal("100.00"))
                                    .available(true)
                                    .car(car1)
                                    .build(),
                            Offer.builder()
                                    .title(car2.getBrand() + car2.getModel())
                                    .price(new BigDecimal("150.00"))
                                    .available(false)
                                    .car(car2)
                                    .build()));
        };
    }
}
