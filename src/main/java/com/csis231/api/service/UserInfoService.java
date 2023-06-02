package com.csis231.api.service;


import com.csis231.api.model.UserInfo;
import com.csis231.api.repository.UserInfoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {

    @Autowired
    private UserInfoRepository userinfoRepository;

    public UserInfo addUser(@Valid UserInfo info) {
        userinfoRepository.save(info);
        return info;
    }
}