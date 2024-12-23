package com.tject.service;

import com.tjetc.common.JsonResult;
import com.tjetc.common.vo.admin.AdminParam;
import com.tjetc.common.vo.admin.HouseParam;

public interface AdminService {
   JsonResult seletPageHouse(String adress, Integer pageNo, Integer pageSize);
   JsonResult findByIdhouse(Long id);
    JsonResult addHouse(HouseParam houseParam);
    //分界线--------------------上面是house------------------------------------------
    JsonResult findById(Long id);

    JsonResult login(String username, String password);
    JsonResult seletPageAdmin(String username,Integer pageNo,Integer pageSize);
   JsonResult deleteAdminById(Long id);
   JsonResult addAdmin(AdminParam admin);
   JsonResult register(String username, String password, String realname,String phonenumber);

}
