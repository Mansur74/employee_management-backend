package com.example.app.mappers;

import com.example.app.dtos.UserDetailDto;
import com.example.app.entities.UserDetail;

public class UserDetailMapper {
    public static UserDetail mapToUserDetail(UserDetailDto userDetailDto) {
        return UserDetail
                .builder()
                .id(userDetailDto.getId())
                .gender(userDetailDto.getGender())
                .imgURL(userDetailDto.getImgURL())
                .birthdate(userDetailDto.getBirthdate())
                .location(userDetailDto.getLocation())
                .phoneNumber(userDetailDto.getPhoneNumber())
                .build();
    }

    public static UserDetailDto mapToUserDetailsDto(UserDetail userDetail) {
        return UserDetailDto
                .builder()
                .id(userDetail.getId())
                .gender(userDetail.getGender())
                .imgURL(userDetail.getImgURL())
                .birthdate(userDetail.getBirthdate())
                .location(userDetail.getLocation())
                .phoneNumber(userDetail.getPhoneNumber())
                .build();
    }
    public static UserDetail updateUserDetail(UserDetailDto userDetailDto, UserDetail userDetail)
    {
        userDetail.setGender(userDetailDto.getGender());
        userDetail.setBirthdate(userDetailDto.getBirthdate());
        userDetail.setImgURL(userDetailDto.getImgURL());
        userDetail.setLocation(userDetailDto.getLocation());
        userDetail.setPhoneNumber(userDetailDto.getPhoneNumber());
        return userDetail;
    }
}
