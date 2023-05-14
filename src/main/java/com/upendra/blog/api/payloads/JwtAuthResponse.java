package com.upendra.blog.api.payloads;

import com.upendra.blog.api.dtos.UserDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthResponse {

	private String token;
	
	private UserDto user;

}
