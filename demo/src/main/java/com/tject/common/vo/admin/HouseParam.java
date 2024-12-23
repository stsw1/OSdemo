package com.tject.common.vo.admin;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HouseParam {
    private Long id;
    private String adress;
    private String imagePath;
    private int floor;
    private String layout;
    private int needMoney;
    private String state;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
}
