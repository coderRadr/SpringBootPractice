package com.portal.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString(exclude = {"areaOfExpertise", "yearsOfExperience"})
@NoArgsConstructor
@AllArgsConstructor
public class ProfileModel {

	private String empId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private List<String> areaOfExpertise;
	
	private Integer yearsOfExperience;
}
