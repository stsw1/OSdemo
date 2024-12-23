package com.tject.controller;

import com.tjetc.common.FileUploadUtil;
import com.tjetc.common.JsonResult;
import com.tjetc.common.vo.admin.HouseParam;
import com.tjetc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
@RestController
@RequestMapping("house")
public class HouseController {

        @Autowired
        private AdminService adminService;
        @Value("${file.basePath}")
        private  String basepath;

        @RequestMapping("detail/{id}")
        public JsonResult detail(@PathVariable("id") Long id) {
            return adminService.findByIdhouse(id);
        }
        @RequestMapping("page")
        public  JsonResult page(@RequestParam(value = "adress",required = false,defaultValue = "")String adress,
                                @RequestParam(value = "pagenum", required = false,defaultValue = "1")Integer pageNum,
                                @RequestParam(value = "pagesize", required = false,defaultValue = "2")Integer pageSize)
        {

            return adminService.seletPageHouse(adress,pageNum,pageSize);
        }
        @RequestMapping("delate/{id}")
        public  JsonResult delate(@PathVariable("id") Long id) {


            return adminService.deleteAdminById(id);
        }
        @RequestMapping("image")
        public JsonResult uploadimage(@RequestParam("imageFile") MultipartFile multipartFile){


            String patth= FileUploadUtil.upload(multipartFile,basepath,"image");
            return JsonResult.success(patth);
        }
        @RequestMapping("save")
        public JsonResult save(@RequestBody HouseParam houseParam){

            return adminService.addHouse(houseParam);
        }
    }


