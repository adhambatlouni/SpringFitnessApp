package com.csis231.api.controller;

import com.csis231.api.model.User;
import com.csis231.api.model.UserInfo;
import com.csis231.api.service.UserInfoService;
import com.csis231.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/userInfo")
public class InfoController {

    @Autowired
    private UserInfoService userinfoService;

    @PostMapping("/add")
    public ResponseEntity<UserInfo> addUserInfo(@Valid @RequestBody UserInfo info, BindingResult result) {
        if(result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        UserInfo savedUserInfo = userinfoService.addUser(info);
        return new ResponseEntity<UserInfo>(savedUserInfo, HttpStatus.CREATED);
    }
}
