package com.harmontronics.lego.entity;

import lombok.Data;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-13 14:07
 */

@Data
public class Device {

    private Integer id;
    private String name; //设备名称
    private String iotDeviceId; //设备唯一标识
    private String firm; //另名称 未知
    private Integer status; ///0:待机， 1:生产中, -2:离线， -3:故障
}
