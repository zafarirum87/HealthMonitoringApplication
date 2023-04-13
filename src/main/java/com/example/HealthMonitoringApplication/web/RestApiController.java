package com.example.HealthMonitoringApplication.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
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

	// 1) Rest Login method
	@RequestMapping(value = "/login")
	public List<AppUser> login() {
		return (List<AppUser>) userRepository.findAll();
	}

	@RequestMapping(value = "/logout")
	public List<AppUser> logout() {
		return (List<AppUser>) userRepository.findAll();
	}

	// 2) list users bp Rest data
	@RequestMapping(value = "/userData")
	public List<AppUser> userListRest(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		String name = userDetails.getUsername();
		AppUser user = userRepository.getUserByName(name);

		List<BloodPressure> bloodPressures = bpRepository.getAllByUser(user);

		List<Exercise> exercises = exRepository.getAllByUser(user);

		List<Weight> weight = weightRepository.getAllByUser(user);

		model.addAttribute("user", user);
		model.addAttribute("usersBP", bloodPressures);
		model.addAttribute("usersExercise", exercises);
		model.addAttribute("usersweight", weight);

		return (List<AppUser>) userRepository.findAll();
	}

}
