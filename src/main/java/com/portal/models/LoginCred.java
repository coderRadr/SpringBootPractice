package com.portal.models;

import lombok.*;

@Getter
@Setter
@ToString(exclude = "password")
@NoArgsConstructor
@AllArgsConstructor
public class LoginCred {
	private String userId;
	private String password;
}
