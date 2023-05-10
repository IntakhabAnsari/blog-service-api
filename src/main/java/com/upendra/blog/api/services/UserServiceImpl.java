package com.upendra.blog.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.upendra.blog.api.dtos.UserDto;
import com.upendra.blog.api.entities.User;
import com.upendra.blog.api.exceptions.ResourceNotFoundException;
import com.upendra.blog.api.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public void createUser(UserDto userDto) {
		User saveUser = dtoToUser(userDto);
		userRepository.save(saveUser);
	}

	@Override
	public List<UserDto> getUsers() {
		List<User> users = userRepository.findAll();
		return users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
	}

	@Override
	public UserDto getUserById(Integer id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		return userToDto(user);
	}

	@Override
	public void updateUser(Integer id, UserDto userDto) {
		User updateUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		dtoToUser(userDto);
		userRepository.save(updateUser);
	}

	@Override
	public void deleteUser(Integer id) {
		User deleteUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
		userRepository.delete(deleteUser);
	}

	private User dtoToUser(UserDto userDto) {
		if (userDto.equals(null)) {
			return null;
		}
		User user = modelMapper.map(userDto, User.class);
		return user;
	}

	private UserDto userToDto(User user) {
		if (user.equals(null)) {
			return null;
		}
		UserDto userDto = modelMapper.map(user, UserDto.class);
		return userDto;
	}

}
