
package com.example.HealthMonitoringApplication.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {

	List<Exercise> findByExerciseName(String exerciseName);

	List<Exercise> exerciseDate(String exerciseDate);

	List<Exercise> getAllByUser(AppUser user);

	List<Exercise> getAllByUser(String name);

}
