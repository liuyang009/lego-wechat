package com.harmontronics.lego.messagequeue;

import com.harmontronics.lego.common.dto.SimpleOperationDto;
import com.harmontronics.lego.util.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * User: draw
 * Date: 2017/7/26 10:25
 */

@Service
public class ProductMqListener {

    private final static String UPDATE_ORDER_INFO_QUEUE = "smartvehicle.topic.v1.order";

    @Autowired
    JmsTemplate jmsTemplate;

    public void sendCreateOrder(SimpleOperationDto simpleOperationDto) {
        jmsTemplate.convertAndSend(UPDATE_ORDER_INFO_QUEUE, JsonMapper.toJsonString(simpleOperationDto));
    }


}
