package com.harmontronics.lego.service;

import com.github.pagehelper.PageHelper;
import com.harmontronics.lego.entity.Device;
import com.harmontronics.lego.mapper.DeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-13 14:31
 */
@Service
@Transactional
public class DeviceService {

    @Autowired
    private DeviceMapper deviceMapper;

    public List<Device> findAll(){
        PageHelper.startPage(1,10);
        return deviceMapper.findAll();
    }

    public void update(Device device) {
        deviceMapper.update(device);
    }

    public Device findById(Integer deviceId) {
        return deviceMapper.findById(deviceId);
    }
}
