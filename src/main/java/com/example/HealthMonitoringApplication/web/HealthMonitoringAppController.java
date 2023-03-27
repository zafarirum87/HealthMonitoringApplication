package com.example.HealthMonitoringApplication.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.HealthMonitoringApplication.domain.AppUser;
import com.example.HealthMonitoringApplication.domain.AppUserRepository;
import com.example.HealthMonitoringApplication.domain.BloodPressure;
import com.example.HealthMonitoringApplication.domain.BloodPressureRepository;
import com.example.HealthMonitoringApplication.domain.Exercise;
import com.example.HealthMonitoringApplication.domain.ExerciseRepository;
import com.example.HealthMonitoringApplication.domain.Weight;
import com.example.HealthMonitoringApplication.domain.WeightRepository;

@Controller
public class HealthMonitoringAppController {
	@Autowired
	private AppUserRepository userRepository;

	@Autowired
	private WeightRepository weightRepository;
	@Autowired
	private ExerciseRepository exRepository;

	@Autowired
	private BloodPressureRepository bpRepository;

	// 1) Login method
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/logout")
	public String logout() {
		return "login";
	}

	// 2) list users data
	@RequestMapping(value = "/userList")
	public String userList(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		String name = userDetails.getUsername();
		AppUser user = userRepository.getUserByName(name);

		List<BloodPressure> bloodPressures = bpRepository.getAllByUser(user);

		List<Exercise> exercises = exRepository.getAllByUser(user);

		List<Weight> weight = weightRepository.getAllByUser(user);

		model.addAttribute("user", user);
		model.addAttribute("usersBP", bloodPressures);
		model.addAttribute("usersExercise", exercises);
		model.addAttribute("usersweight", weight);

		return "userList";
	}

	// 3) Add users new BP data

	@RequestMapping("/addNewData")
	public String save(Model model) {
		model.addAttribute("bloodPressure", new BloodPressure());
		return "addNewData";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@AuthenticationPrincipal UserDetails userDetails, BloodPressure bloodPressure) {

		String name = userDetails.getUsername();
		AppUser user = userRepository.getUserByName(name);

		bloodPressure.setUser(user);

		bpRepository.save(bloodPressure);
		return "redirect:userList";
	}

	// edit BP data

	@RequestMapping(value = "/editBP/{bloodPressureId}", method = RequestMethod.GET)
	public String edit(@PathVariable("bloodPressureId") Long bloodPressureId, Model model) {
		model.addAttribute("bp", bpRepository.findById(bloodPressureId));
		return "editBP";
	}

	@RequestMapping(value = "/editBP/{bloodPressureId}", method = RequestMethod.POST)
	public String saveEdit(@PathVariable("bloodPressureId") Long bloodPressureId,
			@AuthenticationPrincipal UserDetails userDetails, BloodPressure bp) {

		String name = userDetails.getUsername();
		AppUser user = userRepository.getUserByName(name);
		bp.setUser(user);

		bp.setBloodPressureId(bloodPressureId);
		bpRepository.save(bp);
		return "redirect:../userList";

	}

	// delete BP data
	@RequestMapping(value = "/delete/{bloodPressureId}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("bloodPressureId") Long bloodPressureId, Model model) {
		bpRepository.deleteById(bloodPressureId);
		return "redirect:../bookList";
	}

}
