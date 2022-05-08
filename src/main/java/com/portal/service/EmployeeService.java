package com.portal.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portal.models.ProfileModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class EmployeeService {

    private final List<ProfileModel> allProfiles;

    public EmployeeService(List<ProfileModel> allProfiles) {
        try {
            ClassPathResource resource = new ClassPathResource("data/employee_profiles.json");
            File file = resource.getFile();
			this.allProfiles = new ObjectMapper().readValue(file, new TypeReference<List<ProfileModel>>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public List<ProfileModel> getAllEmployeeDtls() {
        return allProfiles;
    }

    public ProfileModel getEmployeeDtlsById(String id) {
        String empId = String.format("DEV000%s_US", id);
        return allProfiles.parallelStream().filter(src ->
                StringUtils.equals(src.getEmpId(), empId)).findAny().
                orElseThrow(() -> new RestClientException("No records found with given Id: " + id));
    }
}
