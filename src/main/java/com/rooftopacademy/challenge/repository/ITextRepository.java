package com.rooftopacademy.challenge.repository;

import com.rooftopacademy.challenge.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITextRepository extends JpaRepository<Text, Long> {
    Text findByHash(String hash);
    List<Text> findAllByCharsAndActive(Integer chars, Integer active, Pageable pageable);
}
