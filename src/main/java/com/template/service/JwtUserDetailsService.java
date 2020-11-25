package com.template.service;

import com.template.model.User;
import com.template.model.UserDto;
import com.template.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userDao;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				new ArrayList<>());
	}

	public User save(UserDto user) throws Exception {
		
		User usuario = userDao.findByUsername(user.getUsername());
		
		if(usuario == null) {
			User newUser = new User();
			newUser.setUsername(user.getUsername());
			newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
			newUser.setTelefone(user.getTelefone());
			newUser.setEmail(user.getEmail());
			
			Calendar calendar = Calendar.getInstance();
		    newUser.setData_cadastro(calendar.getTime());
			
			return userDao.save(newUser);
		}else {
			throw new Exception("Usuário já existente na base.");
		}
			
		
		
	}
}