
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

/*creating blood pressure entity class that store and get systolic value,
 * diastolic value, and pulse rate value of the user.
 * And also store and get time and date for blood pressure values.*/
@Validated
@Entity
public class BloodPressure {

	// define primary key and generate id automatically in database

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bloodPressureId;

	@NotNull(message = "cannot be empty")
	@Min(value = 0)
	private int systolic;

	@NotNull
	@Min(value = 0)
	private int diastolic;

	@NotNull
	@Min(value = 0)
	private int pulseRate;

	@NotBlank
	private String date;

	@NotBlank
	private String time;

	// --- creating relationship to user table---

	@ManyToOne
	@JoinColumn(name = "id")
	private AppUser user;

	// ----- constructor-------
	public BloodPressure(int systolic, int diastolic, int pulseRate, String date, String time, AppUser user) {
		super();
		this.systolic = systolic;
		this.diastolic = diastolic;
		this.pulseRate = pulseRate;
		this.date = date;
		this.time = time;
		this.user = user;
	}

	public BloodPressure() {
	}

	// -------- getters and setters---------

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Long getBloodPressureId() {
		return bloodPressureId;
	}

	public void setBloodPressureId(Long bloodPressureId) {
		this.bloodPressureId = bloodPressureId;
	}

	public int getSystolic() {
		return systolic;
	}

	public void setSystolic(int sysotolic) {
		this.systolic = sysotolic;
	}

	public int getDiastolic() {
		return diastolic;
	}

	public void setDiastolic(int diastolic) {
		this.diastolic = diastolic;
	}

	public int getPulseRate() {
		return pulseRate;
	}

	public void setPulseRate(int pulseRate) {
		this.pulseRate = pulseRate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
