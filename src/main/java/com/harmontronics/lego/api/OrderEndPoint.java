package com.harmontronics.lego.api;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.harmontronics.lego.common.response.ResponseDto;
import com.harmontronics.lego.common.response.ResponseDtoFactory;
import com.harmontronics.lego.constants.MediaTypes;
import com.harmontronics.lego.entity.Order;
import com.harmontronics.lego.entity.Stage;
import com.harmontronics.lego.entity.WeChatUser;
import com.harmontronics.lego.service.LocaleMessageSourceService;
import com.harmontronics.lego.service.OrderService;
import com.harmontronics.lego.service.StageService;
import com.harmontronics.lego.service.WeChatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-14 9:27
 */

@Api(value = "/order", description = "订单管理接口")
@RestController
public class OrderEndPoint {

    @Autowired
    private OrderService orderService;

    @Autowired
    private WeChatService weChatService;

    @Autowired
    private StageService stageService;

    @Autowired
    private LocaleMessageSourceService localeMessageSourceService;

    private String getMessage(int code) {
        return localeMessageSourceService.getMessage("smartcar.order." + code);
    }

    @ApiOperation(value = "创建订单接口")
    @RequestMapping(value = "/api/v1/orders"
            , produces = MediaTypes.JSON_UTF_8
            , method = RequestMethod.POST)
    public ResponseDto createOrder(@RequestParam() Integer meterialBodyId
            , @RequestParam() Integer meterialHeadId
            , @RequestParam() Integer meterialBoxId
            , @RequestParam() Long weChatId){
        WeChatUser weChatUser = weChatService.findById(weChatId);
        if(weChatUser.getBalance() == 0){
            return ResponseDtoFactory.getErrorResponseDto("1000",getMessage(1000));
        }else{
            weChatUser.setBalance(0);
            weChatService.update(weChatUser);
        }
        if (weChatUser == null){
            return ResponseDtoFactory.getErrorResponseDto("1100",getMessage(1100));
        }
        orderService.create(weChatUser
                , meterialBodyId
                ,meterialHeadId
                ,meterialBoxId);
        return ResponseDtoFactory.getSuccessResponseDto(getMessage(1200));
    }

    @ApiOperation(value = "获取订单列表")
    @RequestMapping(value = "/api/v1/orders"
            , produces = MediaTypes.JSON_UTF_8
            , method = RequestMethod.GET)
    public ResponseDto listOrder(
            @ApiParam(value = "WeChat用户ID") @RequestParam() Integer id,
            @ApiParam(value = "页") @RequestParam() Integer page,
            @ApiParam(value = "页数",defaultValue = "5") @RequestParam() Integer pageSize
    ){
        PageInfo<Order> orderPage  = orderService.findByAccountId(id,page,pageSize);
        return ResponseDtoFactory.getResponseDto(true,orderPage);
    }


    @ApiOperation(value = "查看订单详情")
    @RequestMapping(value = "/api/v1/orders/{id}"
            , produces = MediaTypes.JSON_UTF_8
            , method = RequestMethod.GET)
    public ResponseDto viewOrder(@ApiParam("订单id")@PathVariable Integer id){
        Map<String,Object> response = Maps.newHashMap();
        Order order = orderService.get(id);
        List<Stage> stageList = stageService.findByOrderId(id);
        response.put("order",order);
        response.put("stageList",stageList);
        return ResponseDtoFactory.getResponseDto(true,response);
    }


    @ApiOperation(value = "取消订单")
    @RequestMapping(value = "/api/v1/orders/{id}"
            , produces = MediaTypes.JSON_UTF_8
            , method = RequestMethod.DELETE)
    public ResponseDto cancelOrder(@PathVariable Integer id){
        orderService.cancelOrder(id);
        return ResponseDtoFactory.getSuccessResponseDto("取消订单指令已发送");
    }

    @ApiOperation(value = "获取订单当前阶段 currentStage 0 待生产 1 组装车身 2 组装车头 3 组装车厢 4 已完成")
    @RequestMapping(value = "/api/v1/order/{id}/stage"
            , produces = MediaTypes.JSON_UTF_8
            , method = RequestMethod.GET)
    public ResponseDto getOrderStage(@PathVariable Integer id){
        Order order = orderService.get(id);
        return ResponseDtoFactory.getResponseDto(true,order);
    }

}
