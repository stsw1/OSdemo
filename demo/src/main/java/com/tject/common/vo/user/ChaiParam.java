package com.tject.common.vo.user;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ChaiParam {
    private Long id;
    private String adress;
    private String imagePath;
    private int floor;
    private String layout;
    private int wantMoney;
    private String state;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Long uid;

}
