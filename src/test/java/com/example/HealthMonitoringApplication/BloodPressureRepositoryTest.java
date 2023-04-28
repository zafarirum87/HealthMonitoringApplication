/*
 * package com.example.HealthMonitoringApplication;
 * 
 * import static org.assertj.core.api.Assertions.assertThat; import static
 * org.junit.jupiter.api.Assertions.assertEquals; import static
 * org.junit.jupiter.api.Assertions.assertNotNull;
 * 
 * import java.util.List;
 * 
 * import org.junit.jupiter.api.Test; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.boot.test.context.SpringBootTest; import
 * com.example.HealthMonitoringApplication.domain.AppUserRepository; import
 * com.example.HealthMonitoringApplication.domain.BloodPressure; import
 * com.example.HealthMonitoringApplication.domain.BloodPressureRepository;
 * 
 * @SpringBootTest class BloodPressureRepositoryTest {
 * 
 * @Autowired BloodPressureRepository bpRepository;
 * 
 * @Autowired AppUserRepository userRepo;
 * 
 * // 1)test add BP
 * 
 * @Test public void AddBP() { BloodPressure bp = new BloodPressure(120, 80, 90,
 * "20:03:2023", "02:12", userRepo.findById(1).get(0)); bpRepository.save(bp);
 * assertThat(bp.getBloodPressureId()).isNotNull(); }
 * 
 * // 2) Retrieve bp list entries for specific users test
 * 
 * @Test public void getBloodPressureEntriesByUser() { List<BloodPressure>
 * bpList = bpRepository.getAllByUser(userRepo.findById(1).get(0));
 * assertThat(bpList.get(0).getUser()).isNotNull();
 * assertThat(bpList.size()).isGreaterThan(0); }
 * 
 * // 3)update bp
 * 
 * @Test public void updateBloodPressureEntry() { // Create a new BloodPressure
 * entry BloodPressure bp = new BloodPressure(120, 80, 90, "20:03:2023",
 * "02:12", userRepo.findById(1).get(0)); bpRepository.save(bp);
 * 
 * // Update the BloodPressure entry bp.setSystolic(130); bp.setDiastolic(85);
 * bpRepository.save(bp);
 * 
 * // Verify that the update was successful BloodPressure updatedBp =
 * bpRepository.findById(bp.getBloodPressureId()).orElse(null);
 * assertNotNull(updatedBp); assertEquals(130, updatedBp.getSystolic());
 * assertEquals(85, updatedBp.getDiastolic()); }
 * 
 * // 4)delete bp entry
 * 
 * @Test public void deleteBloodPressureEntry() { // Create a new BloodPressure
 * entry BloodPressure bp = new BloodPressure(120, 80, 90, "20:03:2023",
 * "02:12", userRepo.findById(1).get(0)); bpRepository.save(bp);
 * 
 * // get bp id and delete the BloodPressure entry Long id =
 * bp.getBloodPressureId(); bpRepository.deleteById(id);
 * 
 * // Verify that the bp is deleted was successful
 * assertThat(bpRepository.findById(id)).isNotPresent(); }
 * 
 * }
 */