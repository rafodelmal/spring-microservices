package com.spring.microservices.photoapp.api.users.domain;

import com.spring.microservices.photoapp.api.users.domain.users.UserDto;
import com.spring.microservices.photoapp.api.users.domain.users.UsersPasswordEncoder;

public class User extends Entity {

	private final String firstName;
	private final String lastName;
	private final Email email;
	private final Password password;
	
	public User(String fistName, String lastName, String email, String password) {
		super();
		this.firstName = fistName;
		this.lastName = lastName;
		this.email = new Email(email);
		this.password = new Password(password);
	}
	
	public static User of(UserDto userData) {
		User user = new User(
				userData.getFirstName(), 
				userData.getLastName(), 
				userData.getEmail(),
				userData.getPassword());
		return user;
	}
	
	public UserDto toDto() {
		UserDto dto = new UserDto();
		dto.setId(id.toString());
		dto.setFirstName(firstName);
		dto.setLastName(lastName);
		dto.setEmail(email.toString());
		dto.setPassword(password.getEncrypted());
		dto.setCreatedAt(createdAt.toString());
		return dto;
	}
	
	public void encryptPassword(UsersPasswordEncoder passwordEncoder) {
		this.password.encrypt(passwordEncoder);
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
	
	public Email getEmail() {
		return email;
	}

	public Password getPassword() {
		return password;
	}

}
