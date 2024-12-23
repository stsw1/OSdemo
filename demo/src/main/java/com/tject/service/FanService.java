package com.tject.service;

import com.tjetc.common.JsonResult;
import com.tjetc.common.vo.user.FanParam;

public interface FanService {
    JsonResult seletPageHouse(String adress, Integer pageNo, Integer pageSize);
    JsonResult findByIdhouse(Long id);
    JsonResult addFan(FanParam fanParam);
    JsonResult deleteAdminById(Long id);

    JsonResult seletcard(Long id);
}
