package com.ua.fishingforum.marks.repository;

import com.ua.fishingforum.marks.model.Mark;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MarkRepository extends JpaRepository<Mark, Long> {

    boolean existsByLatAndLng(double lat, double lng);
}
