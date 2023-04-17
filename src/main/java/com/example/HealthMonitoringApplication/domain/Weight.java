
package com.example.HealthMonitoringApplication.domain;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/* creating weight entity class that store and get weight value and date of the
 user.*/
@Validated
@Entity
public class Weight {

	// define primary key and generate id automatically in database

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long weightId;

	@NotNull(message = "cannot be empty")
	@Min(value = 0)
	private double weight;

	@NotBlank(message = "cannot be empty")
	private String date;

	// --- creating relationship to user table---

	@ManyToOne
	@JoinColumn(name = "id")
	private AppUser user;

	// ---- constructor ----
	public Weight(double weight, String date, AppUser user) {
		super();
		this.weight = weight;
		this.date = date;
		this.user = user;
	}

	public Weight() {
	}

	// --- getters and setters ---
	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Long getWeightId() {
		return weightId;
	}

	public void setWeightId(Long weightId) {
		this.weightId = weightId;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
