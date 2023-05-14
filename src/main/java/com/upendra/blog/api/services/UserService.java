package com.upendra.blog.api.services;

import java.util.List;

import com.upendra.blog.api.dtos.UserDto;

public interface UserService {

	List<UserDto> getUsers();

	UserDto getUserById(Integer id);

	void createUser(UserDto userDto);

	void updateUser(Integer id, UserDto userDto);

	void deleteUser(Integer id);

	UserDto registerNewUser(UserDto userDto);

}
