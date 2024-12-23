package com.tject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.entity.User;
import org.apache.ibatis.annotations.Param;

//BaseMapper 提供很多方法让我们使用，例如 单表操作的增删改查
public interface UserMapper extends BaseMapper<User> {
   User selectByUsernameAndPassword(@Param("username") String username,
                                    @Param("password") String password);
   Page<User> selectPageLikeUsername(@Param("username") String username, @Param("adminPage") Page<User> adminPage);
   User selectByUsername(@Param("username") String username);

}


