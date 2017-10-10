package com.harmontronics.lego.entity;

import lombok.Data;

import java.util.Date;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-12 15:25
 */
@Data
public class WeChatUser {

    private Long id; // 非业务主键

    private String nickname; // 昵称

    //用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。
    private String headimgurl;

    private Integer sex; //值为1时是男性，值为2时是女性，值为0时是未知

    private Integer balance; //账户积分

    private String openid; // 用户的标识，对当前公众号唯一

    private Date createDate;//创建时间
}
