package com.harmontronics.lego.api;

import com.google.common.collect.Maps;
import com.harmontronics.lego.common.response.ResponseDto;
import com.harmontronics.lego.common.response.ResponseDtoFactory;
import com.harmontronics.lego.constants.Constants;
import com.harmontronics.lego.constants.MediaTypes;
import com.harmontronics.lego.entity.Device;
import com.harmontronics.lego.entity.WeChatUser;
import com.harmontronics.lego.service.DeviceService;
import com.harmontronics.lego.service.LocaleMessageSourceService;
import com.harmontronics.lego.service.OrderService;
import com.harmontronics.lego.service.WeChatService;
import com.harmontronics.lego.util.DateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-17 14:02
 */
@Api(value = "/account", description = "用户管理接口")
@RestController
public class WeChatUserEndPoint {

    @Autowired
    private WeChatService weChatService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private LocaleMessageSourceService localeMessageSourceService;

    private String getMessage(int code) {
        return localeMessageSourceService.getMessage("smartcar.order." + code);
    }



    @ApiOperation(value = "扫码新增用户积分")
    @RequestMapping(value = "/api/v1/accounts/{id}"
            , produces = MediaTypes.JSON_UTF_8
            , method = RequestMethod.PUT)
    public ResponseDto updateWeChatUserByScore(
            @PathVariable long id){
        WeChatUser weChatUser = weChatService.findById(id);
        if (null == weChatUser){
            return ResponseDtoFactory.getErrorResponseDto("0001",getMessage(1100));
        }
        weChatUser.setBalance(Constants.HUNDRED_BALANCE);
        weChatService.update(weChatUser);
        return ResponseDtoFactory.getSuccessResponseDto(localeMessageSourceService.getMessage("add_balance_success"));
    }


    @ApiOperation(value = "获取当前用户信息")
    @RequestMapping(value = "/api/v1/accounts/{id}"
            , produces = MediaTypes.JSON_UTF_8
            , method = RequestMethod.GET)
    public ResponseDto accounts(@PathVariable Integer id){
        WeChatUser user = weChatService.findById(id);
        if (null == user){
            return ResponseDtoFactory.getErrorResponseDto("0001",getMessage(1100));
        }
        //获取订单数
        int orderTotals = orderService.getTotalOrders(id);
        Map<String,Object> params = Maps.newHashMap();
        params.put("account",user);
        params.put("orderTotals",orderTotals);
        return ResponseDtoFactory.getResponseDto(true,params);
    }

}
