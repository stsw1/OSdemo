package com.tject.service;

import com.tjetc.common.JsonResult;
import com.tjetc.common.vo.admin.AdminParam;

public interface BossService {

    //分界线--------------------上面是house------------------------------------------
    JsonResult findById(Long id);

    JsonResult seletPageAdmin(String username,Integer pageNo,Integer pageSize);
   JsonResult deleteAdminById(Long id);
   JsonResult addAdmin(AdminParam admin);


}
