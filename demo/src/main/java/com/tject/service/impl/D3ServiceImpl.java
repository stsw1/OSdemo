package com.tject.service.impl;

import com.tjetc.common.JsonResult;
import com.tjetc.common.vo.admin.D3;
import com.tjetc.common.vo.admin.Zhu;
import com.tjetc.common.vo.admin.ZhuColloction;
import com.tjetc.dao.D3Mapper;
import com.tjetc.entity.House;
import com.tjetc.service.D3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class D3ServiceImpl implements D3Service {
@Autowired
private D3Mapper d3Mapper;

    @Override
    public JsonResult totol() {
        List<D3>d3s=d3Mapper.selectList2();

        return JsonResult.success(d3s);
    }

    @Override
    public JsonResult buidling(String lou) {
       Long T= d3Mapper.update2(lou);
       return JsonResult.success("房子建好啦");
    }

    @Override
    public JsonResult zhu() {
        List<ZhuColloction>zhus=d3Mapper.selectGroupedIds();
        List<Zhu> zhuList = new ArrayList<>();
        for(ZhuColloction zhu:zhus){
            List<House> t1=d3Mapper.selectHousesByIds(zhu.getIdList());
            Zhu zhu1=new Zhu();
            zhu1.setOne(t1.get(0));
            zhu1.setTwo(t1.get(1));
            zhu1.setThree(t1.get(2));
            zhu1.setFour(t1.get(3));
            zhu1.setFive(t1.get(4));

            zhuList.add(zhu1);
        }

        return JsonResult.success(zhuList);
    }
}
