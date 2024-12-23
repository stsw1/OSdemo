package com.tject.controller;

import com.tjetc.common.JsonResult;
import com.tjetc.entity.Message;
import com.tjetc.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("message")
public class MessageControl {
@Autowired
MessageService messageService;
    @Value("${file.basePath}")
    private  String basepath;
    @RequestMapping("read")
    public JsonResult read() {
        return  messageService.readMessage();
    }

    @RequestMapping("write")

    public JsonResult write( @RequestBody Message message) {
      return messageService.writeMessage(message);
    }
    @RequestMapping("save")

    public JsonResult save(@RequestParam("id")Long id) {

        return messageService.saveMessage(id);
    }


}
