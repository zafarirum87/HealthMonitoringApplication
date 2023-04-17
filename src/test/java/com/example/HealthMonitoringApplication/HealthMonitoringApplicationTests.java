package com.example.HealthMonitoringApplication;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.HealthMonitoringApplication.web.HealthMonitoringAppController;

@SpringBootTest
class HealthMonitoringApplicationTests {

	@Autowired
	private HealthMonitoringAppController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
