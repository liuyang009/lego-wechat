package com.harmontronics.lego.wechat.controller;

import com.harmontronics.lego.common.response.ResponseDto;
import com.harmontronics.lego.common.response.ResponseDtoFactory;
import com.harmontronics.lego.constants.MediaTypes;
import com.harmontronics.lego.service.LocaleMessageSourceService;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-17 16:02
 */
@RestController
@Slf4j
public class WeChatJsApiController {

    @Autowired
    private WxMpService wxService;

    @Autowired
    private LocaleMessageSourceService localeMessageSourceService;

    private String getMessage(int code) {
        return localeMessageSourceService
                .getMessage("smartcar.wechat.error." + code);
    }
    /**
     * 获取微信JS API 签名
     * @param url
     * @return
     */
    @RequestMapping(value = "/wx/signature"
            ,produces = MediaTypes.JSON_UTF_8
            , method = RequestMethod.POST)
    public ResponseDto getSignature(
            @ApiParam(value ="location.href.split('#')[0]")@RequestParam String url) throws UnsupportedEncodingException {
        Assert.hasText(url, "URL不能为空");
        log.info("\n微信扫一扫，接受到的URL=[{}],编码url:[{}]",url,URLDecoder.decode(url, "UTF-8"));
        try {
            WxJsapiSignature jsapiSignature = wxService.createJsapiSignature(URLDecoder.decode(url, "UTF-8"));
            return ResponseDtoFactory.getResponseDto(true,jsapiSignature);
        } catch (WxErrorException e) {
            log.error("\n获取微信JS API 签名失败",e);
        }
        return ResponseDtoFactory.getErrorResponseDto("10010",getMessage(10010));
    }
}
