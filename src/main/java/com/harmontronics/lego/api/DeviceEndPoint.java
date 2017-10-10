package com.harmontronics.lego.api;

import com.harmontronics.lego.common.response.ResponseDto;
import com.harmontronics.lego.common.response.ResponseDtoFactory;
import com.harmontronics.lego.constants.MediaTypes;
import com.harmontronics.lego.entity.Device;
import com.harmontronics.lego.service.DeviceService;
import com.harmontronics.lego.service.LocaleMessageSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-13 14:23
 */

@Api(value = "/device", description = "设备管理接口")
@RestController
public class DeviceEndPoint {

    @Autowired
    private DeviceService deviceService;

    @ApiOperation(value = "获取设备列表 ")
    @RequestMapping(value = "/api/v1/devices"
            , produces = MediaTypes.JSON_UTF_8
            , method = RequestMethod.GET)
    public ResponseDto listDevice() {
        List<Device> deviceList = deviceService.findAll();
        return ResponseDtoFactory.getResponseDto(true, deviceList);
    }


}
