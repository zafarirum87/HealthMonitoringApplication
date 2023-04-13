package com.example.HealthMonitoringApplication.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	// create new user account using sign up form
	@GetMapping("/signUp")
	public String showSignInForm(Model model) {
		model.addAttribute("user", new AppUser());
		return "signUp";
	}

	@PostMapping("/signUp")
	public String processSignInForm(@ModelAttribute("user") AppUser user) {
		AppUser existingUser = userRepository.findByName(user.getName());
		if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
			return "redirect:/signUp?error";
		} else {
			userRepository.save(user);
			return "redirect:/userList";
		}
	}

	// 1) Login method
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
	public String save(@AuthenticationPrincipal UserDetails userDetails, BloodPressure bloodPressure) {

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
			@AuthenticationPrincipal UserDetails userDetails, BloodPressure bp) {

		String name = userDetails.getUsername();
		AppUser user = userRepository.getUserByName(name);
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
	public String saveExercise(@AuthenticationPrincipal UserDetails userDetails, Exercise exercise) {

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
			@AuthenticationPrincipal UserDetails userDetails, Exercise exercise) {

		String name = userDetails.getUsername();
		AppUser user = userRepository.getUserByName(name);
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
		model.addAttribute("weight", new Weight());
		return "addWeight";
	}

	@RequestMapping(value = "/saveWeight", method = RequestMethod.POST)
	public String saveWeight(@AuthenticationPrincipal UserDetails userDetails, Weight weight) {

		String name = userDetails.getUsername();
		AppUser user = userRepository.getUserByName(name);

		weight.setUser(user);

		weightRepository.save(weight);
		return "redirect:userList";
	}

	// 10) edit weight data

	@RequestMapping(value = "/editWeight/{weightId}", method = RequestMethod.GET)
	public String editWeight(@PathVariable("weightId") Long weightId, Model model) {
		model.addAttribute("weight", weightRepository.findById(weightId));
		return "editWeight";
	}

	@RequestMapping(value = "/editWeight/{weightId}", method = RequestMethod.POST)
	public String saveEditWeight(@PathVariable("weightId") Long weightId,
			@AuthenticationPrincipal UserDetails userDetails, Weight weight) {

		String name = userDetails.getUsername();
		AppUser user = userRepository.getUserByName(name);
		weight.setUser(user);

		weight.setWeightId(weightId);
		weightRepository.save(weight);
		return "redirect:../userList";

	}

	// 8) delete weight data
	@RequestMapping(value = "/deleteWeight/{weightId}", method = RequestMethod.GET)
	public String deleteWeight(@PathVariable("weightId") Long weightId, Model model) {
		weightRepository.deleteById(weightId);
		return "redirect:../userList";
	}

//	// ******------------RESTFUL API-----------***********
//	// 1) list users bp data
//	@RequestMapping(value = "/userData")
//	public @ResponseBody List<AppUser> userListRest(@AuthenticationPrincipal UserDetails userDetails, Model model) {
//		String name = userDetails.getUsername();
//		AppUser user = userRepository.getUserByName(name);
//
//		List<BloodPressure> bloodPressures = bpRepository.getAllByUser(user);
//
//		List<Exercise> exercises = exRepository.getAllByUser(user);
//
//		List<Weight> weight = weightRepository.getAllByUser(user);
//
//		model.addAttribute("user", user);
//		model.addAttribute("usersBP", bloodPressures);
//		model.addAttribute("usersExercise", exercises);
//		model.addAttribute("usersweight", weight);
//
//		return (List<AppUser>) userRepository.findAll();
//	}

}
