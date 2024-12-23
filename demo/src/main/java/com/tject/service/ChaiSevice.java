package com.tject.service;

import com.tjetc.common.JsonResult;
import com.tjetc.common.vo.user.ChaiParam;

public interface ChaiSevice {

    JsonResult select(String username);

    JsonResult addChai(ChaiParam chaiParam);



    JsonResult examine(Long id, Long flag);

    JsonResult adselect(String adress, String state);

    JsonResult delete(Long id);
}
