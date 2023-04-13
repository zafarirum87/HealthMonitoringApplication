package com.example.HealthMonitoringApplication.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.HealthMonitoringApplication.domain.AppUser;
import com.example.HealthMonitoringApplication.domain.AppUserRepository;

import io.micrometer.common.util.StringUtils;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	private final AppUserRepository repository;

	@Autowired
	public UserDetailServiceImpl(AppUserRepository userRepository) {
		this.repository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		if (StringUtils.isEmpty(username)) {
			throw new IllegalArgumentException("Username cannot be null or empty.");
		}
		AppUser curruser = repository.findByName(username);
		if (curruser == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPassword(),
				AuthorityUtils.createAuthorityList(curruser.getRole()));
		return user;
	}

	public void createUser(String username, String password, String role) {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new IllegalArgumentException("Username, and password, cannot be null or empty.");
		}
		AppUser user = new AppUser();
		user.setName(username);
		user.setPassword(passwordEncoder().encode(password));
		user.setRole(role);
		repository.save(user);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
