/*
 * package com.example.HealthMonitoringApplication;
 * 
 * import static org.assertj.core.api.Assertions.assertThat; import static
 * org.junit.jupiter.api.Assertions.assertEquals; import static
 * org.junit.jupiter.api.Assertions.assertNotNull;
 * 
 * import java.util.List;
 * 
 * import org.junit.jupiter.api.Test; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * com.example.HealthMonitoringApplication.domain.AppUserRepository; import
 * com.example.HealthMonitoringApplication.domain.Exercise; import
 * com.example.HealthMonitoringApplication.domain.ExerciseRepository;
 * 
 * @SpringBootTest class ExerciseRepositoryTest {
 * 
 * @Autowired ExerciseRepository exRepository;
 * 
 * @Autowired AppUserRepository userRepo;
 * 
 * // 1)test add exercise
 * 
 * @Test public void AddBP() { Exercise exercise = new Exercise("lifting",
 * "2:00", "20:03:2023", userRepo.findById(1).get(0));
 * exRepository.save(exercise);
 * assertThat(exercise.getExerciseId()).isNotNull(); }
 * 
 * // 2) Retrieve exercise list entries for specific users test
 * 
 * @Test public void getExerciseByUser() { List<Exercise> exerciseList =
 * exRepository.getAllByUser(userRepo.findById(1).get(0));
 * assertThat(exerciseList.get(0).getUser()).isNotNull();
 * assertThat(exerciseList.size()).isGreaterThan(0); }
 * 
 * // 3)update exercise
 * 
 * @Test public void updateBloodPressureEntry() { // Create a new exercise entry
 * Exercise exercise = new Exercise("lifting", "2:00", "20:03:2023",
 * userRepo.findById(1).get(0)); exRepository.save(exercise);
 * 
 * // Update the exercise entry bp.setSystolic(130); bp.setDiastolic(85);
 * exRepository.save(exercise);
 * 
 * // Verify that the update was successful Exercise updatedExercise =
 * exRepository.findById(exercise.getExerciseId()).orElse(null);
 * assertNotNull(updatedExercise); assertEquals("lifting",
 * updatedExercise.getExerciseName()); assertEquals("2:00",
 * updatedExercise.getExerciseHours());
 * 
 * }
 * 
 * // 4)delete bp entry
 * 
 * @Test public void deleteBloodPressureEntry() { // Create a new exercise entry
 * Exercise exercise = new Exercise("lifting", "2:00", "20:03:2023",
 * userRepo.findById(1).get(0)); exRepository.save(exercise);
 * 
 * // get bp id and delete the exercise entry Long id =
 * exercise.getExerciseId(); exRepository.deleteById(id);
 * 
 * // Verify that the exercise is deleted was successful
 * assertThat(exRepository.findById(id)).isNotPresent(); }
 * 
 * }
 */