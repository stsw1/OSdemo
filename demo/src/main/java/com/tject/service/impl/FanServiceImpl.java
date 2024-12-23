package com.tject.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.common.vo.user.FanParam;
import com.tjetc.dao.*;
import com.tjetc.entity.Fan;
import com.tjetc.entity.House;
import com.tjetc.service.FanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class FanServiceImpl implements FanService {

    @Value("${token.expiration}")
    private int tokeExpiration;

    @Autowired
    private  ChaiMapper chaiMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HousesMapper housesMapper;
    @Autowired
    private FanMapper fanMapper;

    public JsonResult seletPageHouse(String adress, Integer pageNo, Integer pageSize) {
        Page<House> housePage = Page.of(pageNo, pageSize);
        Page<House> houses= housesMapper.selectPageFan(adress, housePage);



        housePage.setRecords(houses.getRecords());
        System.out.println(housePage);
        return JsonResult.success(housePage);
    }

    public JsonResult findByIdhouse(Long id) {
        //selectById是mybatisplus提供的单表查询操作，生成sql语句都是mybatisplus内置了
        House house = housesMapper.selectById(id);

        return JsonResult.success(house);
    }
    @Transactional
    @Override
    public JsonResult addFan(FanParam fanParam) {
        Fan fan=new Fan();
        House house=housesMapper.selectOneByAdress(fanParam.getAdress());
        BeanUtils.copyProperties(fanParam,fan);
        fan.setHid(house.getId());
        LocalDateTime now = LocalDateTime.now();
        fan.setCreateTime(now);
        fan.setUpdateTime(now);
        System.out.println(fan);
        int t=fanMapper.insert(fan);
        int te=fanMapper.insertuh(fan.getId(),fanParam.getUid());
        int t3=fanMapper.examineupdate(fan.getHid(),"已返迁",now);
        int t4=fanMapper.changeuser(fanParam.getUid(),house.getId());
        return JsonResult.success("申请成功");

    }

    public  JsonResult deleteAdminById(Long id) {
        int i = userMapper.deleteById(id);
        if (i == 1) {
            return JsonResult.success(i);
        }
        else {
            return JsonResult.fail("删除失败，查无此人");
        }

    }

    @Override
    public JsonResult seletcard(Long id) {

        List<House> houses= housesMapper.selectcard(id);

        return JsonResult.success(houses);
    }


}
