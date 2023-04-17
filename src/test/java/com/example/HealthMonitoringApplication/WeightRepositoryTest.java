package com.example.HealthMonitoringApplication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.HealthMonitoringApplication.domain.AppUserRepository;
import com.example.HealthMonitoringApplication.domain.Weight;
import com.example.HealthMonitoringApplication.domain.WeightRepository;


@SpringBootTest
class WeightRepositoryTest {

	@Autowired
	WeightRepository weightRepo;

	@Autowired
	AppUserRepository userRepo;

	// 1)test add weight
	@Test
	public void AddBP() {
		Weight weight = new Weight(64.2, "20:03:2023", userRepo.findByName("abc"));
		weightRepo.save(weight);
		assertThat(weight.getWeightId()).isNotNull();
	}

	// 2) Retrieve weight list entries for specific users test

	@Test
	public void getExerciseByUser() {
		List<Weight> weightList = weightRepo.getAllByUser(userRepo.findById(1).get(0));
		assertThat(weightList.get(0).getUser()).isNotNull();
		assertThat(weightList.size()).isGreaterThan(0);
	}

	// 3)update exercise

	@Test
	public void updateweightEntry() {
		// Create a new weight entry
		Weight weight = new Weight(64.2, "20:03:2023", userRepo.findByName("abc"));
		weightRepo.save(weight);

		// Update the weight entry
		weight.setWeight(65);
		;
		weightRepo.save(weight);

		// Verify that the update was successful
		Weight updatedWeight = weightRepo.findById(weight.getWeightId()).orElse(null);
		assertNotNull(updatedWeight);
		assertEquals(65, updatedWeight.getWeight());

	}

	// 4)delete weight entry

	@Test
	public void deleteWeightEntry() {
		// Create a new weight entry
		Weight weight = new Weight(64.2, "20:03:2023", userRepo.findByName("abc"));
		weightRepo.save(weight);

		// get weight id and delete the entry
		Long id = weight.getWeightId();
		weightRepo.deleteById(id);

		// Verify that the weight entry is deleted successfully
		assertThat(weightRepo.findById(id)).isNotPresent();
	}

}
