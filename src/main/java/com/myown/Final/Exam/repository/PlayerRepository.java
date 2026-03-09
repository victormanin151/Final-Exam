package com.myown.Final.Exam.repository;

import com.myown.Final.Exam.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByFullNameContainingIgnoreCase(String fullName);
}
