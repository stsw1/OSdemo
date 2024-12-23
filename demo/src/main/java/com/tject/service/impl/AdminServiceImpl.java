package com.tject.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.common.JwtTokenUtil;
import com.tjetc.common.vo.admin.AdminParam;
import com.tjetc.common.vo.admin.HouseParam;
import com.tjetc.dao.AdminMapper;
import com.tjetc.dao.HousesMapper;
import com.tjetc.dao.UserMapper;
import com.tjetc.entity.Admin;
import com.tjetc.entity.House;
import com.tjetc.entity.User;
import com.tjetc.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {
    @Value("${token.expiration}")
    private int tokeExpiration;

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HousesMapper housesMapper;

    public JsonResult seletPageHouse(String adress, Integer pageNo, Integer pageSize) {
        Page<House> housePage = Page.of(pageNo, pageSize);
        Page<House> houses= housesMapper.selectPageLikeAdress(adress, housePage);



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
    public JsonResult addHouse(HouseParam houseParam) {
        House house=null;     LocalDateTime localDate = LocalDateTime.now();
        if(houseParam.getId()==null||houseParam.getId()<=0){
            if(StringUtils.isAnyBlank(houseParam.getAdress())){

                return JsonResult.fail("没有地址，新增失败");
            }
            house=new House();
            BeanUtils.copyProperties(houseParam,house) ;
            house.setCreateTime(localDate);
            house.setUpdateTime(localDate);
            housesMapper.insert(house);
            return JsonResult.success("新增成功",null);
        }

        else{
            house=housesMapper.selectById(houseParam.getId()) ;
            if(house==null){
                return JsonResult.fail("查无此人，更新失败");
            }
            house.setUpdateTime(localDate);
            house.setImagePath(houseParam.getImagePath());
            house.setLayout(houseParam.getLayout());
            house.setFloor(houseParam.getFloor());
            house.setState(houseParam.getState());
            house.setNeedMoney(houseParam.getNeedMoney());
            int t=housesMapper.updateById(house);
            if(t==0){
                return JsonResult.fail("查无此人，更新失败");
            }
            else {return JsonResult.success("更新成功",null);}
        }


    }
//分界线--------------------上面是house------------------------------------------
@Override
public JsonResult seletPageAdmin(String username, Integer pageNo, Integer pageSize) {
    Page<User> userPage = Page.of(pageNo, pageSize);
//         Page<Admin> admins=
    Page<User>t=userMapper.selectPageLikeUsername(username,userPage);

    userPage.setRecords(t.getRecords());
    return JsonResult.success(userPage);
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
    public JsonResult findById(Long id) {
        //selectById是mybatisplus提供的单表查询操作，生成sql语句都是mybatisplus内置了
     User user = userMapper.selectById(id);


        return JsonResult.success(user);
    }

    @Override
    public JsonResult login(String username, String password) {
        //StringUtils.isAnyBlank 实参中任意一个为空，就返回true
        if (StringUtils.isAnyBlank(username, password)) {
            return JsonResult.fail("用户名或者密码不能为空");
        }
        //根据用户名和密码查询Admin
        Admin admin = adminMapper.selectByUsernameAndPassword(username, password);
        if (admin == null) {
            return JsonResult.fail("用户名或者密码错误");
        } else {
            //用户名或者密码正确，生成token
            Map<String, Object> claims = new HashMap<>();
            claims.put("adminId", admin.getId());
            String token = JwtTokenUtil.generateToken(claims, "admin", tokeExpiration);
            //token和admin对象全部给前端
            return JsonResult.success(token, admin);
        }
    }
@Transactional
    @Override
    public JsonResult addAdmin(AdminParam adminParam) {
        User admin=null;     LocalDateTime localDate = LocalDateTime.now();
        if(adminParam.getId()==null||adminParam.getId()<=0){
            if(StringUtils.isAnyBlank(adminParam.getUsername(),adminParam.getPassword())){

                return JsonResult.fail("没有用户名或密码，新增失败");
            }
            admin=new User();
            BeanUtils.copyProperties(adminParam,admin) ;
            admin.setCreateTime(localDate);
            admin.setUpdateTime(localDate);
            userMapper.insert(admin);
            return JsonResult.success("新增成功",null);
        }

    else{
        admin=userMapper.selectById(adminParam.getId()) ;
        if(admin==null){
            return JsonResult.fail("查无此人，更新失败");
        }
        admin.setUsername(adminParam.getUsername());
        admin.setUpdateTime(localDate);
        admin.setImagePath(adminParam.getImagePath());
        int t=userMapper.updateById(admin);
        if(t==0){
            return JsonResult.fail("查无此人，更新失败");
        }
        else {return JsonResult.success("更新成功",null);}
    }


}
    @Override
    public JsonResult register(String username, String password, String realname, String phonenumber) {

        return null;
    }
}
