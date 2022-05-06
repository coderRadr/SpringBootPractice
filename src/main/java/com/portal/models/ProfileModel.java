package com.portal.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"areaOfExpertise", "yearsOfExperience"})
public class ProfileModel {

    private String firstName;
    private String lastName;
    private String email;
    private List<String> areaOfExpertise;
    private Integer yearsOfExperience;
}
