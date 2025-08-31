package com.reply.Automotive_Platform.repository;

import com.reply.Automotive_Platform.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {}
