package com.tject.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.service.D3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("d3")
public class D3Controller {
    @Autowired
    private D3Service d3Service;
    @RequestMapping("totol")
    public JsonResult totol(){

        return d3Service.totol();
    }
    @RequestMapping("buidling")
    public JsonResult buidlingl(@RequestParam("lou")String lou){

        return d3Service.buidling(lou);
    }
    @RequestMapping("zhu")
    public JsonResult zhu(){

        return d3Service.zhu();
    }
}
