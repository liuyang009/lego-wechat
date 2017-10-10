package com.harmontronics.lego.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Soul on 2017/7/26.
 */
@Data
public class SimpleOperationDto implements Serializable {
    private static final long serialVersionUID = -5548572270931394415L;

    private String operation; // create | cancel

    private String serialNumber; // 订单号
    private Long accountId; // openId
    private String nickname; // nickname
    private Integer gender; // gender
    private String headerProfile; // nickname

    private Integer materialBodyId;
    private Integer materialHeadId;
    private Integer materialBoxId;
}
