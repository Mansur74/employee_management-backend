package com.example.app.business.concretes;
import com.example.app.business.abstracts.UserDetailService;
import com.example.app.core.utilities.results.DataResult;
import com.example.app.core.utilities.results.SuccessDataResult;
import com.example.app.dataAccess.abstracts.UserDao;
import com.example.app.dataAccess.abstracts.UserDetailDao;
import com.example.app.dtos.UserDetailDto;
import com.example.app.entities.UserDetail;
import com.example.app.entities.UserEntity;
import com.example.app.mappers.UserDetailMapper;
import com.example.app.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static com.example.app.mappers.UserDetailMapper.*;

@Service
public class UserDetailManager implements UserDetailService {
    @Autowired
    private UserDetailDao userDetailDao;
    @Autowired
    private UserDao userDao;

    @Override
    public DataResult<UserDetailDto> createUserDetail(UserDetailDto userDetailDto) {
        UserEntity user = userDao.findByEmail(SecurityUtil.getSessionUser());
        UserDetail userDetail = mapToUserDetail(userDetailDto);
        userDetail.setUser(user);
        UserDetail createdUserDetail = userDetailDao.save(UserDetailMapper.updateUserDetail(userDetailDto, userDetail));
        return new SuccessDataResult<>(mapToUserDetailsDto(createdUserDetail));
    }

    @Override
    public DataResult<UserDetailDto> updateUserDetail(UserDetailDto userDetailDto, int userDetailId) {
        String email = SecurityUtil.getSessionUser();
        UserDetail userDetail = userDetailDao.findById(userDetailId).get();
        if(userDetail.getUser().getEmail().equals(email))
        {
            UserDetail updatedUserDetail = userDetailDao.save(UserDetailMapper.updateUserDetail(userDetailDto, userDetail));
            return new SuccessDataResult<>(mapToUserDetailsDto(updatedUserDetail));
        }
        throw new NoSuchElementException();
    }
}
