package com.example.HealthMonitoringApplication.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/*creating user entity class that store and get user-id, user name, password, age and gender.
 * it also lists the blood pressure, weight and exercise record for each user.
*/
@Entity
public class AppUser {

	// define primary key and generate id automatically in database
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name, password, gender;
	private int age;

	// ---creating relation ---
	// 1)--BP entity relation
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<BloodPressure> bloodPressure;

	// 2) exercise entity relation
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Exercise> exercise;

	// constructor
	public AppUser(String name, String password, String gender, int age) {
		super();
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.age = age;
	}

	public AppUser() {
	}
	// --- getters and setters---

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<BloodPressure> getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(List<BloodPressure> bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public List<Exercise> getExercise() {
		return exercise;
	}

	public void setExercise(List<Exercise> exercise) {
		this.exercise = exercise;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ",gender=" + gender + ",age=" + age;
	}
}