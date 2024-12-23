package com.tject.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.common.vo.admin.AdminParam;
import com.tjetc.dao.AdminMapper;
import com.tjetc.dao.BossMapper;
import com.tjetc.entity.Admin;
import com.tjetc.service.BossService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class BossServiceImpl implements BossService {
    @Value("${token.expiration}")
    private int tokeExpiration;

    @Autowired
    private BossMapper bossMapper;
    @Autowired
    private AdminMapper adminMapper;


    public JsonResult seletPageAdmin(String adress, Integer pageNo, Integer pageSize) {
        Page<Admin> housePage = Page.of(pageNo, pageSize);
        Page<Admin> houses= bossMapper. selectPageLikeUsername(adress, housePage);



      housePage.setRecords(houses.getRecords());
        System.out.println(housePage);
        return JsonResult.success(housePage);
    }

    @Override
    public JsonResult deleteAdminById(Long id) {
        int i = bossMapper.deleteById(id);
        if (i == 1) {
            return JsonResult.success(i);
        }
        else {
            return JsonResult.fail("删除失败，查无此人");
        }

    }

    public JsonResult findById(Long id) {
        //selectById是mybatisplus提供的单表查询操作，生成sql语句都是mybatisplus内置了
      Admin admin=bossMapper.selectById(id);

        return JsonResult.success(admin);
    }
    @Transactional
    @Override
    public JsonResult addAdmin(AdminParam admin) {
        LocalDateTime localDate = LocalDateTime.now();
        if(admin.getId()==null||admin.getId()<=0){


            Admin admin2=new Admin();

           admin2.setCreateTime(localDate);
           admin2.setUpdateTime(localDate);
          bossMapper.insert(admin2);
            return JsonResult.success("新增成功",null);
        }

        else{
         Admin admin2=bossMapper.selectById(admin.getId()) ;
            if(admin2==null){
                return JsonResult.fail("查无此人，更新失败");
            }
            admin2.setUsername(admin.getUsername());
            admin2.setPassword(admin.getPassword());
           admin2.setUpdateTime(localDate);
           admin2.setImagePath(admin.getImagePath());
            int t=bossMapper.updateById(admin2);
            if(t==0){
                return JsonResult.fail("查无此人，更新失败");
            }
            else {return JsonResult.success("更新成功",null);}
        }


    }

}
