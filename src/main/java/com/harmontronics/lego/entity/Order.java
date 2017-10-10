package com.harmontronics.lego.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-20 14:54
 */

@Data
public class Order {

    private Integer id;
    private Long accountId;
    private String serialNumber;
    private Integer status; // -2 取消 -1 下单 0 正在生产 5 完成
    private int currentStage; // 0 待生产 1 组装车身 2 组装车头 3 组装车厢 4 已完成
    private Date createDate;

    private List<Material> materialList;




}
