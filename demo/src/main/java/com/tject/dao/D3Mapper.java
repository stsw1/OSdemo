package com.tject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjetc.common.vo.admin.D3;
import com.tjetc.common.vo.admin.ZhuColloction;
import com.tjetc.entity.House;

import java.util.List;

public interface D3Mapper extends BaseMapper<D3> {


    List<D3> selectList2();

   Long update2(String lou);
   List<ZhuColloction> selectGroupedIds();
   List<House>selectHousesByIds(List<Integer> ids);
}
