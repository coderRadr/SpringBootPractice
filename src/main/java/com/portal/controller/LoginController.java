package com.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.portal.models.LoginCred;
import com.portal.models.ProfileModel;
import com.portal.service.LoginService;

@RestController(value = "/")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PutMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProfileModel loginToPortal(@RequestBody LoginCred cred){
        return loginService.login(cred);
    }

}
