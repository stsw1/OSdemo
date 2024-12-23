package com.tject.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.common.vo.user.ChaiParam;
import com.tjetc.service.ChaiSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chai")
public class ChaiController {
    @Autowired
    private ChaiSevice chaiSevice;
    @Value("${file.basePath}")
    private  String basepath;
    @RequestMapping("select")
    public JsonResult select(@RequestParam(value = "adress",required = false,defaultValue = "")String adress
   , @RequestParam(value = "state",required = false,defaultValue = "")String state)
    {
        return chaiSevice.adselect(adress,state);
    }
    @RequestMapping("save")
    public JsonResult save(@RequestBody ChaiParam chaiParam) {

        return chaiSevice.addChai(chaiParam);
    }

    @RequestMapping("examine")
    public JsonResult examine(@RequestParam("id")Long id,@RequestParam("flag") Long flag) {

        return chaiSevice.examine(id, flag);
    }
}
