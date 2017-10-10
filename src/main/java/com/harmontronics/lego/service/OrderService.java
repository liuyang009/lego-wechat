package com.harmontronics.lego.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.harmontronics.lego.common.dto.SimpleOperationDto;
import com.harmontronics.lego.entity.*;
import com.harmontronics.lego.mapper.OrderMapper;
import com.harmontronics.lego.mapper.WeChatUserMapper;
import com.harmontronics.lego.messagequeue.ProductMqListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedArray;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-14 10:06
 */
@Service
@Transactional
@Slf4j
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private WeChatUserMapper weChatUserMapper;

    @Autowired
    private ProductMqListener productMqListener;

    @Autowired
    private OrderMaterialService orderMaterialService;

    @Autowired
    private MasService masService;

    @Autowired
    private StageService stageService;

    /**
     * 通知生产端创建订单
     * @param weChatUser
     * @param materialBodyId
     * @param materialHeadId
     * @param materialBoxId
     */
    public boolean create(WeChatUser weChatUser
            , Integer materialBodyId
            , Integer materialHeadId
            , Integer materialBoxId) {
        Order order = new Order();
        order.setAccountId(weChatUser.getId());
        order.setStatus(-1); //下单
        order.setCurrentStage(0); //待生产
        order.setSerialNumber("XS-" + DateUtils.formatDate(new Date(), "yyyyMMddHHMMssSSS"));
        orderMapper.insert(order);
        //插入tb_stage
        for (int i = 0; i <5 ; i++) {
            Stage stage = new Stage();
            stage.setStageSeq(i);
            stage.setOrderId(Math.toIntExact(order.getId()));
            stage.setNodeName(Stage.get(i));
            stageService.insert(stage);
        }
        //插入tb_order_material
        OrderMaterial orderMeteria = new OrderMaterial();
        orderMeteria.setOrderId(order.getId());
        orderMeteria.setMaterialId(materialBodyId);
        orderMaterialService.insertOrderMaterial(orderMeteria);
        orderMeteria = new OrderMaterial();
        orderMeteria.setOrderId(order.getId());
        orderMeteria.setMaterialId(materialHeadId);
        orderMaterialService.insertOrderMaterial(orderMeteria);
        orderMeteria = new OrderMaterial();
        orderMeteria.setOrderId(order.getId());
        orderMeteria.setMaterialId(materialBoxId);
        orderMaterialService.insertOrderMaterial(orderMeteria);
        SimpleOperationDto simpleOperationDto = new SimpleOperationDto();
        simpleOperationDto.setAccountId(weChatUser.getId());
        simpleOperationDto.setSerialNumber(order.getSerialNumber());
        simpleOperationDto.setGender(weChatUser.getSex());
        simpleOperationDto.setHeaderProfile(weChatUser.getHeadimgurl());
        simpleOperationDto.setMaterialBodyId(materialBodyId);
        simpleOperationDto.setMaterialBoxId(materialBoxId);
        simpleOperationDto.setMaterialHeadId(materialHeadId);
        simpleOperationDto.setNickname(weChatUser.getNickname());
        simpleOperationDto.setOperation("create");
        log.info("\n发送到MQ的订单信息：msg={}",   new Gson().toJson(simpleOperationDto));
        productMqListener.sendCreateOrder(simpleOperationDto);
        return true;
    }

    public PageInfo<Order> findByAccountId(Integer id
            , int page
            , int pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Order> orderList = orderMapper.findByAccountId(id);
        orderList.stream().forEach(order -> {
            List<Material> materialList = masService.findByOrderId(order.getId());
             order.setMaterialList(materialList);
        });
        return new PageInfo<Order>(orderList);
    }
    /**
     * 取消订单
     * @param id 订单id
     * @return
     */
    public void cancelOrder(Integer id) {
        Order order = orderMapper.get(id);
        Long accountId = order.getAccountId();
        WeChatUser weChatUser = weChatUserMapper.findById(accountId);
        SimpleOperationDto simpleOperationDto = new SimpleOperationDto();
        simpleOperationDto.setAccountId(weChatUser.getId());
        simpleOperationDto.setSerialNumber(order.getSerialNumber());
        simpleOperationDto.setGender(weChatUser.getSex());
        simpleOperationDto.setHeaderProfile(weChatUser.getHeadimgurl());
        simpleOperationDto.setNickname(weChatUser.getNickname());
        simpleOperationDto.setOperation("cancel");
        productMqListener.sendCreateOrder(simpleOperationDto);
        orderMapper.updateOrderStatus(id);
    }

    /**
     * 获取用户的订单数
     * @param id 用户ID
     * @return 订单数
     */
    public int getTotalOrders(Integer id) {
        List<Order> orderList = orderMapper.findByAccountId(id);
        if (null == orderList || orderList.isEmpty()){
            return 0;
        }
        return orderList.size();
    }

    /**
     * 获取订单
     * @param id
     * @return
     */
    public Order get(Integer id) {
        Order order = orderMapper.get(id);
        List<Material> materialList = masService.findByOrderId(order.getId());
        order.setMaterialList(materialList);
        return order;
    }

    /**
     * 根据订单号获取订单
     * @param serialNumber
     * @return
     */
    public Order findBySerialNumber(String serialNumber) {
        return orderMapper.findBySerialNumber(serialNumber);
    }

    public void update(Order order) {
        orderMapper.update(order);
    }
}
