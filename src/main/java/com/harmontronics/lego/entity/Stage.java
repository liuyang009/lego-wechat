package com.harmontronics.lego.entity;

import lombok.Data;

import java.util.Date;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-20 13:39
 */

@Data
public class Stage {

    private Integer id; //主键

    private Integer orderId; //订单主键

    private Integer stageSeq; //序列

    private String nodeName; //节点名称

    private Date startDate; //开始时间

    private Date endDate; //结束时间

    private Date preStartDate; //预计开始时间

    private Date preEndDate; //预计结束时间



    public static String get(int i) {
        switch (i){
            case 0:
                return "WAITING";
            case 1:
                return "COMPOSING_BODY";
            case 2:
                return "COMPOSING_HEAD";
            case 3:
                return "COMPOSING_BOX";
            case 4:
                return "COMPLETE";
            default:
                return "UNKNOWN";
        }
    }
}
