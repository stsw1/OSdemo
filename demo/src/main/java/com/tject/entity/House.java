package com.tject.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
//配置实体类对应表名称
@TableName("t_houses")
public class House {
    private Long id;
    private String adress;
    private String imagePath;
    private int floor;
    private String layout;
    @TableField("needmoney")
    private int needMoney;
    private String state;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;



}
