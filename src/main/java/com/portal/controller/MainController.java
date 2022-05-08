package com.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portal.aop.ExecutionTime;
import com.portal.dao.ProfileDao;
import com.portal.models.LoginCred;
import com.portal.models.ProfileModel;
import com.portal.service.LoginService;

@RestController(value = "/")
public class MainController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private ProfileDao profileDao;

	@ExecutionTime
	@PutMapping(value = "/login")
	public ResponseEntity<ProfileModel> loginToPortal(@RequestBody LoginCred cred, HttpServletRequest request,
			HttpServletResponse response) {
		return ResponseEntity.ok(loginService.login(cred));
	}

	@ExecutionTime
	@GetMapping(value = "/getEmployeeDtls")
	public ResponseEntity<List<ProfileModel>> getAllEmployees(HttpServletRequest request,
			HttpServletResponse response) {
		List<ProfileModel> allEmployeeDtls = profileDao.getEmployeeDetails();
		return ResponseEntity.ok(allEmployeeDtls);
	}

}
