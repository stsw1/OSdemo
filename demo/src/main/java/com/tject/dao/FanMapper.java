package com.tject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjetc.entity.Fan;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;

public interface FanMapper  extends BaseMapper<Fan> {

    int insertuh( Long fid, Long uid);

    int examineupdate(@Param("id") Long id, @Param("state") String state,@Param("localDateTime") LocalDateTime localDateTime);

    int changeuser(@Param("id") Long uid,@Param("hid") Long hid);
}
