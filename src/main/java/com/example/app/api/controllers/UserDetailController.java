package com.example.app.api.controllers;

import com.example.app.business.abstracts.UserDetailService;
import com.example.app.business.abstracts.UserService;
import com.example.app.core.utilities.results.DataResult;
import com.example.app.dtos.UserDetailDto;
import com.example.app.entities.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserDetailController {
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    public UserService userService;

    @PostMapping("/my/userDetail")
    public ResponseEntity<DataResult<UserDetailDto>> createUserDetail(@AuthenticationPrincipal User user, @RequestBody UserDetailDto userDetailDto)
    {
        int userId = userService.getUserByEmail(user.getUsername()).getData().getId();
        DataResult<UserDetailDto> result = userDetailService.createUserDetail(userDetailDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/my/userDetail/{userDetailId}")
    public ResponseEntity<DataResult<UserDetailDto>> updateUserDetail(@AuthenticationPrincipal User user, @RequestBody UserDetailDto userDetailDto, @PathVariable int userDetailId)
    {
        int userId = userService.getUserByEmail(user.getUsername()).getData().getId();
        DataResult<UserDetailDto> result = userDetailService.updateUserDetail(userDetailDto, userDetailId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
