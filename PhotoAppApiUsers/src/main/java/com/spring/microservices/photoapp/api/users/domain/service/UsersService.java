package com.spring.microservices.photoapp.api.users.domain.service;

import com.spring.microservices.photoapp.api.users.shared.UserDto;

public interface UsersService {

	void createUser(UserDto userData);
}