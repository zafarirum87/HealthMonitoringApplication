
package com.example.HealthMonitoringApplication.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

/*creating blood pressure entity class that store and get systolic value, 
 * diastolic value, and pulse rate value of the user. 
 * And also store and get time and date for blood pressure values.*/

@Entity
public class BloodPressure {

	// define primary key and generate id automatically in database

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long bloodPressureId;
	private int systolic;
	private int diastolic;
	private int pulseRate;
	private String date;
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
