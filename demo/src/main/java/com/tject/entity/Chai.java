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
@TableName("t_chai")
public class Chai {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long hid;
    @TableField("imagepath")
    private String imagePath;
    @TableField("wantmoney")
    private int wantMoney;
    private String state;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
