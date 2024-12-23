package com.tject.service.impl;

import com.tjetc.common.JsonResult;
import com.tjetc.dao.MessageMapper;
import com.tjetc.entity.Message;
import com.tjetc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Value("${token.expiration}")
    private int tokeExpiration;
   @Autowired
    private MessageMapper messageMapper;

    @Override
    public JsonResult writeMessage(Message message) {
                message.setCreateTime(LocalDateTime.now());
             int t=   messageMapper.insert(message);
             return JsonResult.success(message);
    }

    @Override
    public JsonResult readMessage() {
        List<Message> messages=messageMapper.select();
        return JsonResult.success(messages);
    }
    @Override
    public JsonResult readMessage2(Long id) {
        List<Message> messages=messageMapper.select2(id);
        return JsonResult.success(messages);
    }

    @Override
    public JsonResult saveMessage(Long id) {

     int t= messageMapper.update2(id);
     return JsonResult.success("处理完毕");
    }
}
