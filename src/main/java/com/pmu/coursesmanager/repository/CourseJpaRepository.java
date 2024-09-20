package com.pmu.coursesmanager.repository;

import com.pmu.coursesmanager.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseJpaRepository extends JpaRepository<Course, Long> {
}
