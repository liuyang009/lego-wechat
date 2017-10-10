package com.harmontronics.lego.wechat.controller;

import com.harmontronics.lego.entity.WeChatUser;
import com.harmontronics.lego.service.WeChatService;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

import static com.harmontronics.lego.constants.MediaTypes.TEXT_PLAIN_UTF_8;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-14 15:49
 */

@Controller
public class WechatController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private WxMpService wxService;

    @Autowired
    private WeChatService weChatService;

    @Value("${wechat.mp.callbackurl}")
    private String weChatCallbackUrl;




    @RequestMapping("wechat/portal")
    public String index(@RequestParam String lang) {
        String url = wxService.oauth2buildAuthorizationUrl
                (weChatCallbackUrl, "snsapi_userinfo", lang);
        logger.info("\n微信回调地址：url=[{}]",url);

        return "redirect:"+ url;

    }


    @RequestMapping("wechat/callback")
    public String index(String code, String state) {
        logger.info("\n接收到微信传来的参数：code=[{}],state=[{}]",code,state);
        boolean isFirstLogin = false;
        WeChatUser user = null;
        try {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxService.oauth2getAccessToken(code);
            user = weChatService.findByOpenId(wxMpOAuth2AccessToken.getOpenId());
            //根据openid 查询数据库有没有此条记录
            if (user == null){
                WxMpOAuth2AccessToken accessToken = wxService
                        .oauth2refreshAccessToken(wxMpOAuth2AccessToken.getRefreshToken()); //刷新token
                WxMpUser wxMpUser = wxService.oauth2getUserInfo(accessToken, null);
                user = new WeChatUser();
                if ("男".equals(wxMpUser.getSex())){
                    user.setSex(1);
                }else if ("女".equals(wxMpUser.getSex())){
                    user.setSex(2);
                }else {
                    user.setSex(0);
                }
                user.setBalance(0);
                user.setOpenid(wxMpUser.getOpenId());
                user.setHeadimgurl(wxMpUser.getHeadImgUrl());
                user.setNickname(wxMpUser.getNickname());
                user.setCreateDate(new Date());
                weChatService.save(user);
                isFirstLogin = true;
            }
            logger.info("\n 新增的用户唯一标识：id=[{}]", user.getId());
        } catch (WxErrorException e) {
            logger.error("获取微信用户信息出错",e);
        }

        return "redirect:http://wemake.harmontronics.com/html/index.html?isFirstLogin="+isFirstLogin+"&userId="+user.getId();  //重定向到前端

    }


    /**
     * 校验微信发送消息参数
     */
    @ResponseBody
    @RequestMapping(value = "wechat/validate", method = RequestMethod.GET,produces = TEXT_PLAIN_UTF_8)
    public String validate(String timestamp, String nonce, String signature, String echostr) {
        this.logger.info("\n接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature,
                timestamp, nonce, echostr);

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        if (this.wxService.checkSignature(timestamp, nonce, signature)) {
            return echostr;
        }

        return "非法请求";
    }

}
