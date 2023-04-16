package com.example.HealthMonitoringApplication.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HealthMonitoringApplication.domain.AppUser;
import com.example.HealthMonitoringApplication.domain.AppUserRepository;
import com.example.HealthMonitoringApplication.domain.BloodPressure;
import com.example.HealthMonitoringApplication.domain.BloodPressureRepository;
import com.example.HealthMonitoringApplication.domain.Exercise;
import com.example.HealthMonitoringApplication.domain.ExerciseRepository;
import com.example.HealthMonitoringApplication.domain.Weight;
import com.example.HealthMonitoringApplication.domain.WeightRepository;

@RestController
@RequestMapping("/api")
public class RestApiController {

	@Autowired
	private AppUserRepository userRepository;

	@Autowired
	private WeightRepository weightRepository;
	@Autowired
	private ExerciseRepository exRepository;

	@Autowired
	private BloodPressureRepository bpRepository;

	public class UserDTO {
		private Long id;
		private String username;
		private int age;
		private String gender;
	}

	// get by userName
	@GetMapping("/{username}")
	public ResponseEntity<List<Object>> getUserByUsername(@PathVariable String username) {
		AppUser currUser = userRepository.findByName(username);
		List<BloodPressure> bloodPressures = bpRepository.getAllByUser(currUser);
		List<Exercise> exercises = exRepository.getAllByUser(currUser);
		List<Weight> weight = weightRepository.getAllByUser(currUser);
		List<Object> response = new ArrayList<>();
		response.add(currUser);
		response.add(bloodPressures);
		response.add(exercises);
		response.add(weight);
		return (ResponseEntity.ok(response));
	}

	// list users
	@GetMapping("/userslist")
	public ResponseEntity<List<Object>> getUsersWithRelatedData() {
		List<AppUser> users = (List<AppUser>) userRepository.findAll();

		List<Object> response = new ArrayList<>();
		for (AppUser user : users) {
			List<Object> userData = new ArrayList<>();
			userData.add(user.getName());
			userData.add(user.getAge());
			userData.add(user.getGender());
			response.add(userData);
		}
		return ResponseEntity.ok(response);
	}
}
