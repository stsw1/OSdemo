package com.tject.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
//配置实体类对应表名称
@TableName("t_user")
public class User {
    //@TableId 对表主键字段与属性映射，value是表示字段名称，type是主键类型
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField(value = "username")
    private String username;
    private String password;
    //@TableField 配置表字段与实体类映射关系，配置字段名称
    @TableField("realname")
    private String realName;
    @TableField("image_path")
    private String imagePath;
    //java实体类驼峰命名与数据库字段字段下划线 自动映射（得益于map-underscore-to-camel-case: true这个配置）
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    @TableField("phonenumber")
    private String phoneNumber;
    @TableField("backid")
    private String backId;
}
