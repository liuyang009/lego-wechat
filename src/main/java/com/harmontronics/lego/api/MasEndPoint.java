package com.harmontronics.lego.api;

import com.harmontronics.lego.common.response.ResponseDto;
import com.harmontronics.lego.common.response.ResponseDtoFactory;
import com.harmontronics.lego.constants.MediaTypes;
import com.harmontronics.lego.entity.Material;
import com.harmontronics.lego.service.MasService;
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
 * @create 2017-07-13 17:33
 */
@Api(value = "/mes", description = "物料管理接口")
@RestController
public class MasEndPoint {

    @Autowired
    private MasService masService;

    @ApiOperation(value = "获取所有物料的状态")
    @RequestMapping(value = "/api/v1/mas"
            , produces = MediaTypes.JSON_UTF_8
            , method = RequestMethod.GET)
    public ResponseDto findMasStatus() {
        List<Material> materials = masService.findAll();
        return ResponseDtoFactory.getResponseDto(true, materials);
    }
}
