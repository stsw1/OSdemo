package com.tject.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.JsonResult;
import com.tjetc.common.JwtTokenUtil;
import com.tjetc.common.vo.user.TotolParam;
import com.tjetc.common.vo.user.UserParam;
import com.tjetc.common.vo.user.UserView;
import com.tjetc.dao.HousesMapper;
import com.tjetc.dao.UserMapper;
import com.tjetc.entity.User;
import com.tjetc.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class UserServiceImpl implements UserService {
    @Value("${token.expiration}")
    private int tokeExpiration;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HousesMapper housesMapper;
    @Override
    public JsonResult findById(Long id) {

       User user = userMapper.selectById(id);
       UserView userView = new UserView();
        BeanUtils.copyProperties(user, userView);
        return JsonResult.success(userView);
    }

    @Override
    public JsonResult login(String username, String password) {
        //StringUtils.isAnyBlank 实参中任意一个为空，就返回true
        if (StringUtils.isAnyBlank(username, password)) {
            return JsonResult.fail("用户名或者密码不能为空");
        }
        //根据用户名和密码查询Admin
        User user = userMapper.selectByUsernameAndPassword(username, password);
        if (user == null) {
            return JsonResult.fail("用户名或者密码错误");
        } else {
            //用户名或者密码正确，生成token
            Map<String, Object> claims = new HashMap<>();
            claims.put("adminId", user.getId());
            String token = JwtTokenUtil.generateToken(claims, "user", tokeExpiration);
            //token和admin对象全部给前端
            return JsonResult.success(token, user);
        }
    }

    @Override
    public JsonResult seletPageUser(String username, Integer pageNo, Integer pageSize) {
        Page<User> userPage = Page.of(pageNo, pageSize);
//         Page<Admin> admins=
       userMapper.selectPageLikeUsername(username,userPage);
       IPage<UserView>userViewPage=userPage.convert(new Function<User,UserView>() {
           @Override
           public UserView apply(User user) {
               UserView userView=new UserView();
               BeanUtils.copyProperties(user,userView);
               return userView;
           }

       });
//        adminPage.setRecords(admins);
        System.out.println(userViewPage);
        return JsonResult.success(userViewPage);
    }

    public  JsonResult deleteUserById(Long id) {
        int i = userMapper.deleteById(id);
        if (i == 1) {
            return JsonResult.success(i);
        }
      else {
          return JsonResult.fail("删除失败，查无此人");
        }

    }
    @Transactional
    @Override
    public JsonResult addUser(UserParam userParam) {
        User user = null;
        LocalDateTime localDate = LocalDateTime.now();
        if(userParam.getId() == null || userParam.getId() <= 0){
            if(StringUtils.isAnyBlank(userParam.getUsername(), userParam.getPassword())){

                return JsonResult.fail("没有用户名或密码，新增失败");
            }
            user = new User();
            BeanUtils.copyProperties(userParam, user);
            user.setCreateTime(localDate);
            user.setUpdateTime(localDate);
            userMapper.insert(user);
            return JsonResult.success("新增成功", null);
        }
        else{
            user = userMapper.selectById(userParam.getId());
            if(user == null){
                return JsonResult.fail("查无此人，更新失败");
            }
            user.setUpdateTime(localDate);
            user.setImagePath(userParam.getImagePath());
            int t = userMapper.updateById(user);
            if(t == 0){
                return JsonResult.fail("查无此人，更新失败");
            }
            else {
                return JsonResult.success("更新成功", null);
            }
        }
    }

    @Override
    public JsonResult register(String username, String password, String realname, String phonenumber) {
       User user = new User();
       user.setUsername(username);
       user.setPassword(password);
       user.setRealName(realname);
       user.setPhoneNumber(phonenumber);
        user.setUpdateTime(LocalDateTime.now());
        user.setCreateTime(LocalDateTime.now());
        user.setBackId("[]");
        int i = userMapper.insert(user);
        if(i == 1){
            return JsonResult.success("注册成功");
        }
      else{

        return  JsonResult.fail("注册失败");
        }
    }

    @Override
    public JsonResult totol() {
      TotolParam totolParam=housesMapper.selectTotals();
      return JsonResult.success(totolParam);
    }
}
