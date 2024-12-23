package com.tject.controller;

import com.tjetc.common.FileUploadUtil;
import com.tjetc.common.JsonResult;
import com.tjetc.common.vo.admin.AdminParam;
import com.tjetc.service.BossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("boss")
public class BossController {
    @Autowired
    private BossService bossService;

    @Value("${file.basePath}")
    private  String basepath;

    @RequestMapping("detail/{id}")
    public JsonResult detail(@PathVariable("id") Long id) {
        return  bossService.findById(id);
    }
    @RequestMapping("page")
    public  JsonResult page(@RequestParam(value = "username",required = false,defaultValue = "")String username,
                            @RequestParam(value = "pagenum", required = false,defaultValue = "1")Integer pageNum,
                            @RequestParam(value = "pagesize", required = false,defaultValue = "2")Integer pageSize)
    {

        return  bossService.seletPageAdmin(username,pageNum,pageSize);
    }
    @RequestMapping("delate/{id}")
    public  JsonResult delate(@PathVariable("id") Long id) {


        return  bossService.deleteAdminById(id);
    }
    @RequestMapping("image")
    public JsonResult uploadimage(@RequestParam("imageFile") MultipartFile multipartFile){


      String patth= FileUploadUtil.upload(multipartFile,basepath,"image");
      return JsonResult.success(patth);
}
    @RequestMapping("save")
    public JsonResult save(@RequestBody AdminParam adminParam){

        return  bossService.addAdmin(adminParam);
    }

}
