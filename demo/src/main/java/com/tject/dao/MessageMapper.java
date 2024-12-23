package com.tject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tjetc.entity.Message;

import java.util.List;

public interface MessageMapper extends BaseMapper<Message> {
    List<Message> select();

   int update2(Long id);

    List<Message> select2(Long id);
}
