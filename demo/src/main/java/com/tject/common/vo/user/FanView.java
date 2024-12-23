package com.tject.common.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FanView {
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
