package com.spring.microservices.photoapp.api.users.infrastracture.repository.mongo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.spring.microservices.photoapp.api.users.domain.users.UserRepository;
import com.spring.microservices.photoapp.api.users.domain.users.exception.UserNotFoundException;
import com.spring.microservices.photoapp.api.users.infrastracture.repository.mongo.document.UserDocument;
import com.spring.microservices.photoapp.api.users.shared.UserDto;

@Component
@Primary
public class UserRepositoryMongoImpl implements UserRepository {

	private final SpringDataMongoUserRepository userRepository;

	@Autowired
	public UserRepositoryMongoImpl(final SpringDataMongoUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public void save(UserDto userData) {
		userRepository.save(UserDocument.of(userData));
	}
	
	@Override
	public UserDto findById(String id) throws UserNotFoundException {
		Optional<UserDocument> result = userRepository.findById(id);
		if (!result.isPresent()) {
			throw new UserNotFoundException(id);
		}
		
		UserDocument user = result.get();
		return user.toDto();		
	}

}
