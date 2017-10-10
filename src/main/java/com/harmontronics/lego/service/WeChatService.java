package com.harmontronics.lego.service;

import com.harmontronics.lego.entity.WeChatUser;
import com.harmontronics.lego.mapper.WeChatUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-14 9:47
 */
@Service
@Transactional
public class WeChatService {

    @Autowired
    private WeChatUserMapper mapper;

    public WeChatUser findById(long id){
        return mapper.findById(id);
    }

    public void update(WeChatUser weChatUser) {
        mapper.update(weChatUser);
    }

    public WeChatUser findByOpenId(String openId) {
        return mapper.findByOpenId(openId);
    }

    public void save(WeChatUser user) {
        mapper.insert(user);
    }
}
