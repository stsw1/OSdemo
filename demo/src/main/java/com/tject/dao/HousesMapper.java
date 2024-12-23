package com.tject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tjetc.common.vo.user.TotolParam;
import com.tjetc.entity.House;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//BaseMapper 提供很多方法让我们使用，例如 单表操作的增删改查
public interface HousesMapper extends BaseMapper<House> {

   Page<House> selectPageLikeAdress(@Param("adress") String adress, @Param("housePage") Page<House> housePage);
   House selectOneByAdress(@Param("adress") String adress);

    Page<House> selectPageFan(String adress, Page<House> housePage);

    List<House> selectcard(Long id);
    TotolParam selectTotals();
}


