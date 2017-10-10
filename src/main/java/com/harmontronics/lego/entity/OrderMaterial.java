package com.harmontronics.lego.entity;

import lombok.Data;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-20 16:00
 */

@Data
public class OrderMaterial {

    private Integer id; //主键

    private Integer orderId; //订单ID

    private Integer materialId; //物料ID
}
