package com.tject.service;

import com.tjetc.common.JsonResult;
import com.tjetc.common.vo.user.UserParam;

public interface UserService {

    JsonResult findById(Long id);

    JsonResult login(String username, String password);
    JsonResult seletPageUser(String username,Integer pageNo,Integer pageSize);
   JsonResult deleteUserById(Long id);
   JsonResult addUser(UserParam userParam);
   JsonResult register(String username, String password, String realname,String phonenumber);

    JsonResult totol();
}
