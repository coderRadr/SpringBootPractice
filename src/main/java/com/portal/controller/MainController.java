package com.portal.controller;

import com.portal.aop.ExecutionTime;
import com.portal.models.ProfileModel;
import com.portal.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Main Controller to get Employee Profile Details
 */
@RestController(value = "/")
public class MainController {

	@Autowired
	private EmployeeService service;

	@ExecutionTime
	@GetMapping(value = "/getEmployeeDtls")
	public ResponseEntity<List<ProfileModel>> getAllEmployees(HttpServletRequest request,
			HttpServletResponse response) {
		List<ProfileModel> allEmployeeDtls = service.getAllEmployeeDtls();
		return ResponseEntity.ok(allEmployeeDtls);
	}

	@ExecutionTime
	@GetMapping(value = "/getEmployeeDtls/{id}")
	public ResponseEntity<ProfileModel> getEmployeeById(@PathVariable("id") String id, HttpServletRequest request,
															  HttpServletResponse response) {
		ProfileModel employeeDtls = service.getEmployeeDtlsById(id);
		return ResponseEntity.ok(employeeDtls);
	}

}
