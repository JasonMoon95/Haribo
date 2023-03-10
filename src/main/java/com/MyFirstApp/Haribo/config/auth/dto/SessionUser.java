package com.MyFirstApp.Haribo.config.auth.dto;

import com.MyFirstApp.Haribo.domain.User.AppUser;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(AppUser appUser) {
        this.name = appUser.getName();
        this.email = appUser.getEmail();
        this.picture = appUser.getPicture();
    }
}