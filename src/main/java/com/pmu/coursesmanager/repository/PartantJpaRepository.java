package com.pmu.coursesmanager.repository;


import com.pmu.coursesmanager.domain.Partant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartantJpaRepository extends JpaRepository<Partant, Long> {
}
