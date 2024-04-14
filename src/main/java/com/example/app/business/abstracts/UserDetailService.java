package com.example.app.business.abstracts;

import com.example.app.core.utilities.results.DataResult;
import com.example.app.dtos.UserDetailDto;

public interface UserDetailService {
    public DataResult<UserDetailDto> createUserDetail(UserDetailDto userDetailDto);
    public DataResult<UserDetailDto> updateUserDetail(UserDetailDto userDetailDto, int userDetailId);
}
