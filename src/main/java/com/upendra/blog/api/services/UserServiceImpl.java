package com.upendra.blog.api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.upendra.blog.api.dtos.UserDto;
import com.upendra.blog.api.entities.Role;
import com.upendra.blog.api.entities.User;
import com.upendra.blog.api.exceptions.ResourceNotFoundException;
import com.upendra.blog.api.repositories.RoleRepository;
import com.upendra.blog.api.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

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

	@Override
	public UserDto registerNewUser(UserDto userDto) {

		User user = new User();// modelMapper.map(userDto, User.class);
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());// encoded the password
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);

		// roless
		Optional<Role> role = roleRepository.findByName("ROLE_USER");
		if (role.isEmpty()) {
			role = Optional.ofNullable(new Role());
			role.get().setName("ROLE_USER");
			roleRepository.save(role.get());
		}
		user.getRoles().add(role.get());
		User newUser = userRepository.save(user);
		return this.modelMapper.map(newUser, UserDto.class);
	}

}
