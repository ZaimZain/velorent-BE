package com.devion.velorent.service;

import com.devion.velorent.entity.AppUser;
import com.devion.velorent.enums.Role;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    @Value("${app.auth.enabled:true}")
    private boolean authEnabled;


    public AppUser getCurrentUser(HttpSession session) {

        if (!authEnabled) {

            AppUser devUser = new AppUser();

            devUser.setUserId(1L);
            devUser.setUsername("devAdmin");
            devUser.setFullName("Development Admin");
            devUser.setRole(Role.ADMIN);

            return devUser;
        }


        return (AppUser) session.getAttribute("username");
    }
}