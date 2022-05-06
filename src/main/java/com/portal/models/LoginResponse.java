package com.portal.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private String uniqueId;
    private boolean isAuthorized;
}
