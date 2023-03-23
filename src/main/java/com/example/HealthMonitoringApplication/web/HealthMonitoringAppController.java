package com.example.HealthMonitoringApplication.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.HealthMonitoringApplication.domain.BloodPressureRepository;
import com.example.HealthMonitoringApplication.domain.ExerciseRepository;
import com.example.HealthMonitoringApplication.domain.WeightRepository;

@Controller
public class HealthMonitoringAppController {
	@Autowired
	private WeightRepository weightRepository;
	@Autowired
	private ExerciseRepository exRepository;

	@Autowired
	private BloodPressureRepository bpRepository;

	@RequestMapping(value = { "/userList" })
	public String userList(Model model) {
		model.addAttribute("usersBP", bpRepository.findAll());
		model.addAttribute("usersExercise", exRepository.findAll());
		model.addAttribute("usersweight", weightRepository.findAll());
		return "userList";
	}
}
