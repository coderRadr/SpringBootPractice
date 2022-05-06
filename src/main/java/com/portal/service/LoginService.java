package com.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portal.dao.ProfileDao;
import com.portal.models.LoginCred;
import com.portal.models.ProfileModel;

@Service
public class LoginService {

    @Autowired
    private ProfileDao profileDao;

    public ProfileModel login(LoginCred cred) {
        return profileDao.login(cred.getUserId(), cred.getPassword());
    }

}
