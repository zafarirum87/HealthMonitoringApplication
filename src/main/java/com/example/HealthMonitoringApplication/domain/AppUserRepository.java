package com.example.HealthMonitoringApplication.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

	List<AppUser> findByName(String name);

	Optional<AppUser> findById(Long id);

	List<AppUser> findById(int i);
}
