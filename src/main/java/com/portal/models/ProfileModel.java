package com.portal.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = {"areaOfExpertise", "yearsOfExperience"})
@NoArgsConstructor
@AllArgsConstructor
public class ProfileModel {

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private List<String> areaOfExpertise;
	
	private Integer yearsOfExperience;
}
