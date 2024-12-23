package com.tject.service;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Message;

public interface MessageService {
   JsonResult writeMessage(Message message);
   JsonResult readMessage();
   JsonResult readMessage2(Long id);
   JsonResult saveMessage(Long id);
}
