package com.tject.common.vo.user;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FanParam {
    private Long id;
    private String adress;
    private String imagePath;
    private int floor;
    private String layout;
    private int needMoney;
    private String state;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long uid;

}
