package com.example.java_venerdi_s7.security.details;


import com.example.java_venerdi_s7.entities.Sonda;
import com.example.java_venerdi_s7.repository.SondaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	SondaRepository sr;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Sonda> sonda = sr.findByUsername(username);

		if (sonda.isPresent()) {
			return UserDetailsImpl.build(sonda.get());
		} else {
			throw new UsernameNotFoundException("User Not Found with username: " + username);
		}
	}

}
