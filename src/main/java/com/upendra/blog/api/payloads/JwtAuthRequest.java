package com.upendra.blog.api.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtAuthRequest {

	private String userName;

	private String password;

}
