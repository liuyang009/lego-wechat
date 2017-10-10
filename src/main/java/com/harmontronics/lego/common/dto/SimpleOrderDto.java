package com.harmontronics.lego.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Soul on 2017/7/26.
 */
@Data
public class SimpleOrderDto implements Serializable {
    private static final long serialVersionUID = -1986333454709899121L;

    private String serialNumber; // 订单号
    private String productionSerialNumber; // 生产订单号

    private Integer status; //0:待机, 1:生产中, -2:离线, -3:故障, -5: 订单创建失败
    private Integer mySequence;
    private int currentStage;

    private List<StageDto> stageDtoList;
}
