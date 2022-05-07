package com.portal.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.portal.models.ProfileModel;

@Repository
public class ProfileDao {

    private UserProfileDao userProfileDao;

    public ProfileDao(@Autowired UserProfileDao userProfileDao) {
        this.userProfileDao = userProfileDao;
    }

    public ProfileModel login(String userId, String password) {
        return userProfileDao.getProfileDetails(userProfileDao.getUniqueId(userId, password));
    }

}
