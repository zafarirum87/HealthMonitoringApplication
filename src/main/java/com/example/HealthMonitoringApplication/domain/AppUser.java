package com.example.HealthMonitoringApplication.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/*creating user entity class that store and get user-id, user name, password, age and gender.
 * it also lists the blood pressure, weight and exercise record for each user.
*/
@Entity(name = "users")
public class AppUser {

	// define primary key and generate id automatically in database
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, updatable = false)
	private Long id;

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "gender", nullable = false)
	private String gender;

	@Column(name = "age", nullable = false)
	private int age;

	@Column(name = "role", nullable = false)
	private String role;

	// ---creating relation ---
	// 1)--BP entity relation
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<BloodPressure> bloodPressure;

	// 2) exercise entity relation
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Exercise> exercise;

	// 2) weight entity relation
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private List<Weight> weight;

	// constructor
	public AppUser(String name, String password, String gender, int age, String role) {
		super();
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.age = age;
		this.role = role;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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

	public List<Weight> getWeight() {
		return weight;
	}

	public void setWeight(List<Weight> weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ",gender=" + gender + ",age=" + age;
	}
}
