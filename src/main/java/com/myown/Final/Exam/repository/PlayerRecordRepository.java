package com.myown.Final.Exam.repository;

import com.myown.Final.Exam.model.PlayerRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRecordRepository extends JpaRepository<PlayerRecord, Long> {
}
