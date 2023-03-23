package com.example.HealthMonitoringApplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.HealthMonitoringApplication.domain.AppUser;
import com.example.HealthMonitoringApplication.domain.AppUserRepository;
import com.example.HealthMonitoringApplication.domain.BloodPressure;
import com.example.HealthMonitoringApplication.domain.BloodPressureRepository;
import com.example.HealthMonitoringApplication.domain.Exercise;
import com.example.HealthMonitoringApplication.domain.ExerciseRepository;
import com.example.HealthMonitoringApplication.domain.Weight;
import com.example.HealthMonitoringApplication.domain.WeightRepository;

@SpringBootApplication
public class HealthMonitoringApplication {
	private static final Logger log = LoggerFactory.getLogger(HealthMonitoringApplication.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(HealthMonitoringApplication.class, args);
	}

	@Bean
	public CommandLineRunner HealthAppDemo(AppUserRepository userRepo, BloodPressureRepository bpRepo,
			ExerciseRepository exRepo, WeightRepository wRepo) {
		return (args) -> {
			log.info("save a couple of users");

			// save new users to app-user repository
			AppUser user1 = new AppUser("xyz", "vvv", "male", 22);
			AppUser user2 = new AppUser("abc", "vffv", "female", 32);
			AppUser user3 = new AppUser("cde", "vrv", "male", 42);

			userRepo.save(user1);
			userRepo.save(user2);
			userRepo.save(user3);

			// save new blood pressure objects
			BloodPressure bp1 = new BloodPressure(120, 80, 90, "20:03:2023", "02:12",
					userRepo.findByName("xyz").get(0));
			BloodPressure bp2 = new BloodPressure(130, 90, 90, "23:03:2023", "02:12",
					userRepo.findByName("cde").get(0));
			BloodPressure bp3 = new BloodPressure(120, 80, 90, "24:03:2023", "02:12", userRepo.findById(2).get(0));
			bpRepo.save(bp1);
			bpRepo.save(bp2);
			bpRepo.save(bp3);

			// save new exercise objects
			Exercise exercise1 = new Exercise("running", "2:00", "20:03:2023", userRepo.findByName("abc").get(0));
			Exercise exercise2 = new Exercise("brisk walk", "00:30", "23:03:2023", userRepo.findByName("xyz").get(0));
			Exercise exercise3 = new Exercise("jogging", "1:00", "20:03:2023", userRepo.findByName("cde").get(0));

			exRepo.save(exercise1);
			exRepo.save(exercise2);
			exRepo.save(exercise3);

			// save weight objects
			Weight weight1 = new Weight(64.2, "20:03:2023", userRepo.findByName("abc").get(0));
			Weight weight2 = new Weight(54.2, "23:03:2023", userRepo.findByName("xyz").get(0));
			Weight weight3 = new Weight(74.2, "20:03:2023", userRepo.findByName("cde").get(0));

			wRepo.save(weight1);
			wRepo.save(weight2);
			wRepo.save(weight3);

			log.info("fetch all users");
			for (AppUser user : userRepo.findAll()) {
				log.info(user.toString());
			}
		};
	}

}
