package com.example.HealthMonitoringApplication.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.HealthMonitoringApplication.domain.AppUser;
import com.example.HealthMonitoringApplication.domain.AppUserRepository;
import com.example.HealthMonitoringApplication.domain.BloodPressure;
import com.example.HealthMonitoringApplication.domain.BloodPressureRepository;
import com.example.HealthMonitoringApplication.domain.Exercise;
import com.example.HealthMonitoringApplication.domain.ExerciseRepository;
import com.example.HealthMonitoringApplication.domain.Weight;
import com.example.HealthMonitoringApplication.domain.WeightRepository;

import jakarta.validation.Valid;

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

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// 1) create signUp form
	@RequestMapping(value = "/signUp")
	public String signUp(Model model) {
		model.addAttribute("user", new AppUser());
		return "signUp";
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public String submitSignUp(@ModelAttribute("user") AppUser user, RedirectAttributes redirAttrs) {
		AppUser existingUser = userRepository.findByName(user.getName());
		if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
			redirAttrs.addFlashAttribute("error", "The user already exists.");
			return "redirect:signUp";
		}
		user.setRole("user");
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		userRepository.save(user);
		redirAttrs.addFlashAttribute("success", "Sign-Up successfully.");
		return "redirect:login";

	}

	// 1.1) Login method
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/logout")
	public String logout() {
		return "login";
	}

	// 2) list users bp data
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

	@RequestMapping("/addNewBP")
	public String save(Model model) {
		model.addAttribute("bloodPressure", new BloodPressure());
		return "addNewBP";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@AuthenticationPrincipal UserDetails userDetails, @Valid BloodPressure bloodPressure,
			BindingResult result) {
		if (result.hasErrors()) {
			return "addNewBP";
		}
		String name = userDetails.getUsername();
		AppUser user = userRepository.getUserByName(name);

		bloodPressure.setUser(user);
		bpRepository.save(bloodPressure);

		return "redirect:userList";
	}

	// 4) edit BP data

	@RequestMapping(value = "/editBP/{bloodPressureId}", method = RequestMethod.GET)
	public String edit(@PathVariable("bloodPressureId") Long bloodPressureId, Model model) {
		model.addAttribute("bp", bpRepository.findById(bloodPressureId));
		return "editBP";
	}

	@RequestMapping(value = "/editBP/{bloodPressureId}", method = RequestMethod.POST)
	public String saveEdit(@PathVariable("bloodPressureId") Long bloodPressureId,
			@AuthenticationPrincipal UserDetails userDetails, @Valid @ModelAttribute("bp") BloodPressure bp,
			BindingResult result) {

		String name = userDetails.getUsername();
		AppUser user = userRepository.getUserByName(name);

		if (result.hasErrors()) {
			return "editBP";
		}
		bp.setUser(user);
		bp.setBloodPressureId(bloodPressureId);
		bpRepository.save(bp);

		return "redirect:../userList";
	}

	// 5) delete BP data
	@RequestMapping(value = "/delete/{bloodPressureId}", method = RequestMethod.GET)
	public String deleteBP(@PathVariable("bloodPressureId") Long bloodPressureId, Model model) {
		bpRepository.deleteById(bloodPressureId);
		return "redirect:../userList";
	}

	// 6) Add users new exercise data

	@RequestMapping("/addExerciseData")
	public String saveExercise(Model model) {
		model.addAttribute("exercise", new Exercise());
		return "addExerciseData";
	}

	@RequestMapping(value = "/saveExercise", method = RequestMethod.POST)
	public String saveExercise(@AuthenticationPrincipal UserDetails userDetails,
			@Valid @ModelAttribute("exercise") Exercise exercise, BindingResult result) {

		if (result.hasErrors()) {
			return "addExerciseData";
		}
		String name = userDetails.getUsername();
		AppUser user = userRepository.getUserByName(name);

		exercise.setUser(user);
		exRepository.save(exercise);
		return "redirect:userList";
	}

	// 7) edit exercise data

	@RequestMapping(value = "/editExercise/{exerciseId}", method = RequestMethod.GET)
	public String editExercise(@PathVariable("exerciseId") Long exerciseId, Model model) {
		model.addAttribute("exercise", exRepository.findById(exerciseId));
		return "editExercise";
	}

	@RequestMapping(value = "/editExercise/{exerciseId}", method = RequestMethod.POST)
	public String saveEditExercise(@PathVariable("exerciseId") Long exerciseId,
			@AuthenticationPrincipal UserDetails userDetails, @Valid @ModelAttribute("exercise") Exercise exercise,
			BindingResult result) {
		String name = userDetails.getUsername();
		AppUser user = userRepository.getUserByName(name);

		if (result.hasErrors()) {
			return "editExercise";
		}

		exercise.setUser(user);
		exercise.setExerciseId(exerciseId);
		exRepository.save(exercise);
		return "redirect:../userList";

	}

	// 8) delete exercise data
	@RequestMapping(value = "/deleteEx/{exerciseId}", method = RequestMethod.GET)
	public String deleteExercise(@PathVariable("exerciseId") Long exerciseId, Model model) {
		exRepository.deleteById(exerciseId);
		return "redirect:../userList";
	}

	// 9) Add users new Weight data

	@RequestMapping("/addWeight")
	public String saveWeight(Model model) {
		model.addAttribute("userWeight", new Weight());
		return "addWeight";
	}

	@RequestMapping(value = "/saveWeight", method = RequestMethod.POST)
	public String saveWeight(@AuthenticationPrincipal UserDetails userDetails,
			@Valid @ModelAttribute("userWeight") Weight userWeight, BindingResult result) {

		if (result.hasErrors()) {
			return "addWeight";
		}

		String name = userDetails.getUsername();
		AppUser user = userRepository.getUserByName(name);

		userWeight.setUser(user);

		weightRepository.save(userWeight);
		return "redirect:userList";
	}

	// 10) edit weight data

	@RequestMapping(value = "/editWeight/{weightId}", method = RequestMethod.GET)
	public String editWeight(@PathVariable("weightId") Long weightId, Model model) {
		model.addAttribute("userWeight", weightRepository.findById(weightId));
		return "editWeight";
	}

	@RequestMapping(value = "/editWeight/{weightId}", method = RequestMethod.POST)
	public String saveEditWeight(@PathVariable("weightId") Long weightId,
			@AuthenticationPrincipal UserDetails userDetails, @Valid @ModelAttribute("userWeight") Weight userWeight,
			BindingResult result) {

		String name = userDetails.getUsername();
		AppUser user = userRepository.getUserByName(name);

		if (result.hasErrors()) {
			return "editWeight";
		}
		userWeight.setUser(user);
		userWeight.setWeightId(weightId);
		weightRepository.save(userWeight);
		return "redirect:../userList";

	}

	// 8) delete weight data
	@RequestMapping(value = "/deleteWeight/{weightId}", method = RequestMethod.GET)
	public String deleteWeight(@PathVariable("weightId") Long weightId, Model model) {
		weightRepository.deleteById(weightId);
		return "redirect:../userList";
	}

}
