
package com.example.HealthMonitoringApplication.domain;

import org.springframework.validation.annotation.Validated;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

/*creating exercise entity class that store and get exercise name,
 * exercise hours and date for the user.*/
@Validated
@Entity
public class Exercise {

	// define primary key and generate id automatically in database

	@Id

	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long exerciseId;

	@NotBlank
	private String exerciseName;

	@NotBlank
	private String exerciseHours;

	@NotBlank
	private String exerciseDate;

	// --- creating relationship to user table---

	@ManyToOne

	@JoinColumn(name = "id")
	private AppUser user;

	// constructor
	public Exercise(String exerciseName, String exerciseHours, String exerciseDate, AppUser user) {
		super();
		this.exerciseName = exerciseName;
		this.exerciseHours = exerciseHours;
		this.exerciseDate = exerciseDate;
		this.user = user;
	}

	public Exercise() {
	}

	// --- getters and setters ---
	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Long getExerciseId() {
		return exerciseId;
	}

	public void setExerciseId(Long exerciseId) {
		this.exerciseId = exerciseId;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public String getExerciseHours() {
		return exerciseHours;
	}

	public void setExerciseHours(String exerciseHours) {
		this.exerciseHours = exerciseHours;
	}

	public String getExerciseDate() {
		return exerciseDate;
	}

	public void setExerciseDate(String exerciseDate) {
		this.exerciseDate = exerciseDate;
	}

}
