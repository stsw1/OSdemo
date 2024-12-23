package com.tject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjetc.common.vo.user.ChaiView;
import com.tjetc.entity.Chai;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ChaiMapper extends BaseMapper<Chai> {
  List<ChaiView>selectChai(Long id);
    List<ChaiView>selectChaibyname(@Param("adress") String adress,@Param("state")String state);
  int myupdate(@Param("id") Long id, @Param("imagePath")String imagePath,
               @Param("wantMoney") int wantMoney, @Param("localDate")
               LocalDateTime localDate,@Param("hid")Long hid);
  int examine(@Param("id") Long id,@Param("state") String state,@Param("localDateTime")LocalDateTime localDateTime);
  int examineupdate(@Param("id") Long id,@Param("state") String state,@Param("localDateTime")LocalDateTime localDateTime);

int  insertuq(@Param("uid") Long uid, @Param("qid") Long qid);

  int deleteuq(Long id);
}
