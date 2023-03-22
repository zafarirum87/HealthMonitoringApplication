package com.example.HealthMonitoringApplication.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

	List<Exercise> findByName(String exerciseName);
}
