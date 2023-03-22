package com.example.HealthMonitoringApplication;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.HealthMonitoringApplication.domain.BloodPressure;
import com.example.HealthMonitoringApplication.domain.BloodPressureRepository;
import com.example.HealthMonitoringApplication.domain.Exercise;
import com.example.HealthMonitoringApplication.domain.ExerciseRepository;
import com.example.HealthMonitoringApplication.domain.User;
import com.example.HealthMonitoringApplication.domain.UserRepository;
import com.example.HealthMonitoringApplication.domain.Weight;
import com.example.HealthMonitoringApplication.domain.WeightRepository;

@SpringBootApplication
public class HealthMonitoringApplication {
	private static final Logger log = LoggerFactory.getLogger(HealthMonitoringApplication.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(HealthMonitoringApplication.class, args);
	}

	@Bean
	public CommandLineRunner studentDemo(UserRepository userRepo, BloodPressureRepository BPrepo,
			WeightRepository weightRepo, ExerciseRepository exRepo) {
		return (args) -> {
			log.info("save a couple of users");

			/*
			 * BloodPressure bp1 = new BloodPressure(120, 80, 85, "22,03,2023", "4:19");
			 * Weight weight1 = new Weight(70.2, "22,03,2023"); Exercise exercise1 = new
			 * Exercise("running", "4:19", "22,03,2023");
			 * 
			 * BPrepo.save(bp1); weightRepo.save(weight1); exRepo.save(exercise1);
			 */

			List<BloodPressure> bp = new ArrayList<BloodPressure>();
			bp.add(new BloodPressure(120, 80, 85, "22,03,2023", "4:19"));

			List<Weight> weight = new ArrayList<Weight>();
			weight.add(new Weight(70.2, "22,03,2023"));

			List<Exercise> exercise = new ArrayList<Exercise>();
			exercise.add(new Exercise("running", "4:19", "22,03,2023"));

			User user1 = new User("John", "password", 25, "male", bp, weight, exercise);
			userRepo.save(user1);

			log.info("fetch all users");
			for (User user : userRepo.findAll()) {
				log.info(user.toString());
			}
		};
	}

}
