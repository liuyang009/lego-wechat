package com.harmontronics.lego.messagequeue;


import com.harmontronics.lego.common.dto.SimpleDeviceDto;
import com.harmontronics.lego.common.dto.SimpleMaterialDto;
import com.harmontronics.lego.common.dto.SimpleOrderDto;
import com.harmontronics.lego.common.dto.StageDto;
import com.harmontronics.lego.entity.Device;
import com.harmontronics.lego.entity.Material;
import com.harmontronics.lego.entity.Order;
import com.harmontronics.lego.entity.Stage;
import com.harmontronics.lego.service.DeviceService;
import com.harmontronics.lego.service.MasService;
import com.harmontronics.lego.service.OrderService;
import com.harmontronics.lego.service.StageService;
import com.harmontronics.lego.util.JsonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * User: draw
 * Date: 2017/7/26 10:25
 */

@Service
public class ProductMqService {

    private static Logger logger = LoggerFactory.getLogger(ProductMqListener.class);

    @Resource
    private DeviceService deviceService;

    @Resource
    private MasService masService;

    @Resource
    private OrderService orderService;

    @Resource
    private StageService stageService;

    private final static String UPDATE_DEVICE_INFO_QUEUE = "smartvehicle.topic.v1.devices";
    private final static String UPDATE_MATERIAL_INFO_QUEUE = "smartvehicle.topic.v1.materials";
    private final static String UPDATE_ORDERS_INFO_QUEUE = "smartvehicle.topic.v1.orders";


    @JmsListener(destination = UPDATE_DEVICE_INFO_QUEUE)
    public void handleDevice(String msg) {
//        logger.info("\n接受MQ中设备的状态信息 device=[{}]", msg);
        SimpleDeviceDto simpleDeviceDto = new JsonMapper().fromJson(msg, SimpleDeviceDto.class);
        Device device = new Device();
        device.setId(1);
        device.setStatus(simpleDeviceDto.getStatus());
        deviceService.update(device);
    }

    @JmsListener(destination = UPDATE_MATERIAL_INFO_QUEUE)
    public void handlMaterial(String msg) {
      /*  logger.info("\n接受MQ中物料的信息 material=[{}]", msg);*/
        List<SimpleMaterialDto> simpleMaterialDtoList = Arrays.asList(new JsonMapper().fromJson(msg, SimpleMaterialDto[].class));
        simpleMaterialDtoList.stream().forEach(simpleMaterialDto -> {
            Long id = simpleMaterialDto.getId();
            Boolean available = simpleMaterialDto.getAvailable();
            Material material = masService.findById(id.intValue());
            if (available == null){
                material.setAvailable(false);
            }else {
                material.setAvailable(available);
            }
            masService.updateAvailable(material);
        });

    }


    @JmsListener(destination = UPDATE_ORDERS_INFO_QUEUE)
    public void handlOrders(String msg) {
       logger.info("\n接受MQ中订单的信息 orders=[{}]", msg);
        List<SimpleOrderDto> simpleOrderDtos
                = Arrays.asList(new JsonMapper().fromJson(msg, SimpleOrderDto[].class));
        if (null != simpleOrderDtos){
            //更新订单状态
            simpleOrderDtos.stream().forEach(simpleOrderDto -> {
                String serialNumber = simpleOrderDto.getSerialNumber();//微信端订单号
                List<StageDto> stageDtoList = simpleOrderDto.getStageDtoList();
                Order order = orderService.findBySerialNumber(serialNumber);
                if (order != null){
                    order.setCurrentStage(simpleOrderDto.getCurrentStage());
                    order.setStatus(simpleOrderDto.getStatus());
                    orderService.update(order);
                    List<Stage> stageList = stageService.findByOrderId(order.getId());
                    if (null != stageList && null != stageDtoList){
                        stageList.stream().forEach(stage -> {
                            StageDto stageDto = stageDtoList.stream()
                                    .filter(x -> stage.getStageSeq().intValue()==x.getStageSeq().intValue())
                                    .findAny()
                                    .orElse(null);
                            stage.setEndDate(stageDto.getEndDate());
                            stage.setPreEndDate(stageDto.getPreEndDate());
                            stage.setPreStartDate(stageDto.getPreStartDate());
                            stage.setStartDate(stageDto.getStartDate());
                            stageService.update(stage);
                        });
                    }
                }
            });
        }
    }


}
