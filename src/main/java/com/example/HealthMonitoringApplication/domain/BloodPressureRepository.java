
package com.example.HealthMonitoringApplication.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BloodPressureRepository extends CrudRepository<BloodPressure, Long> {
	List<BloodPressure> findByDate(String date);

	List<BloodPressure> findByTime(String time);

	List<BloodPressure> getAllByUser(AppUser user);

	List<BloodPressure> getAllByUser(String name);

}
