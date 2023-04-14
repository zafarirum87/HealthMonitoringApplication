package com.example.HealthMonitoringApplication.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

		if (curruser != null) {

		}
		;
		UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPassword(),
				AuthorityUtils.createAuthorityList(curruser.getRole()));
		return user;
	}

}
