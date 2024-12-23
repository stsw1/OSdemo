package com.tject.service.impl;

import com.tjetc.common.JsonResult;
import com.tjetc.common.vo.user.ChaiParam;
import com.tjetc.common.vo.user.ChaiView;
import com.tjetc.dao.ChaiMapper;
import com.tjetc.dao.HousesMapper;
import com.tjetc.dao.UserMapper;
import com.tjetc.entity.Chai;
import com.tjetc.entity.House;
import com.tjetc.entity.User;
import com.tjetc.service.ChaiSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChaiServiceImpl implements ChaiSevice {
    @Autowired
    private  ChaiMapper chaiMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    HousesMapper housesMapper;
  public  JsonResult select(String username){
      User user=userMapper.selectByUsername(username);
      if(user==null){
          return  JsonResult.fail("查无此人");
      }
      else {
         List<ChaiView> chaiView=chaiMapper.selectChai(user.getId());

          return JsonResult.success(chaiView);
      }
  }
    public  JsonResult adselect(String adress, String state){
       List<ChaiView> chaiViewList=chaiMapper.selectChaibyname(adress,state);

            return JsonResult.success(chaiViewList);

    }
   @Transactional
    @Override
    public JsonResult delete(Long id) {
      int t1=chaiMapper.deleteById(id);
     int t2= chaiMapper.deleteuq(id);

        return  JsonResult.success("删除成功");
    }

    @Override
    public JsonResult examine(Long id, Long flag) {

      LocalDateTime localDateTime=LocalDateTime.now();
      int t;
       if(flag==1){
           t=chaiMapper.examine(id,"已通过",localDateTime);

           chaiMapper.examineupdate(id,"需拆除",localDateTime);
       }
       else {
           t=chaiMapper.examine(id,"已拒绝",localDateTime);


       }
       if(t==0){return JsonResult.fail("审核失败");
       }return JsonResult.success("审核成功");
    }

    @Transactional
    @Override
    public JsonResult addChai(ChaiParam chaiParam) {
      LocalDateTime localDate = LocalDateTime.now();
        if(chaiParam.getId()==null||chaiParam.getId()<=0){
        House House=housesMapper.selectOneByAdress(chaiParam.getAdress());
        Chai chai=new Chai();
        chai.setHid(House.getId());
        chai.setImagePath(chaiParam.getImagePath());
        chai.setWantMoney(chaiParam.getWantMoney());
        chai.setUpdateTime(localDate);
        chai.setState("待审核");
        chai.setCreateTime(localDate);
        chaiMapper.insert(chai);
        chaiMapper.insertuq(chaiParam.getUid(),chai.getId());
        return JsonResult.success("新增成功");

        }

        else{
            Long id=chaiParam.getId();
            String adress=chaiParam.getAdress();
            House house=housesMapper.selectOneByAdress(adress);
            if(house==null){
                return JsonResult.fail("更新失败 查无此房");
            }
                Long hid=house.getId();
            String imagePath= chaiParam.getImagePath();
           int  wantMoney=chaiParam.getWantMoney();
         chaiMapper.myupdate(id,imagePath,wantMoney,localDate,hid);
        }
        return  JsonResult.success("更新成功");
    }

}
