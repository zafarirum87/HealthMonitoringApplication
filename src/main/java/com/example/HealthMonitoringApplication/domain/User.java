package com.example.HealthMonitoringApplication.domain;

import java.util.ArrayList;
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
public class User {

	// define primary key and generate id automatically in database
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	private String UserName;
	private String password;
	private int age;
	private String gender;

	// --- creating relationship to user table---
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<BloodPressure> bloodPressure = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Weight> weight = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Exercise> exercise = new ArrayList<>();

	// constructor
	public User(String userName, String password, int age, String gender, List<BloodPressure> bloodPressure,
			List<Weight> weight, List<Exercise> exercise) {
		super();
		UserName = userName;
		this.password = password;
		this.age = age;
		this.gender = gender;
		this.bloodPressure = bloodPressure;
		this.weight = weight;
		this.exercise = exercise;
	}

	// --- getters and setters---
	public User() {
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		this.UserName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<BloodPressure> getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(List<BloodPressure> bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public List<Weight> getWeight() {
		return weight;
	}

	public void setWeight(List<Weight> weight) {
		this.weight = weight;
	}

	public List<Exercise> getExercise() {
		return exercise;
	}

	public void setExercise(List<Exercise> exercise) {
		this.exercise = exercise;
	}

}
