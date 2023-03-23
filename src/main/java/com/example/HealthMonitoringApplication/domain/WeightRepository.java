
package com.example.HealthMonitoringApplication.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface WeightRepository extends CrudRepository<Weight, Long> {

	List<Weight> findByDate(String date);
}
