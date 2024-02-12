package com.example.microservices.movieservice.repositories;
import com.example.microservices.movieservice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
