package com.harmontronics.lego.entity;

import lombok.Data;

/**
 * ${DESCRIPTION}
 *  小车各组件物料
 * @author Five.Liu
 * @create 2017-07-20 16:06
 */

@Data
public class Material {

    private Integer id; //主键

    private String name;//名称 body,head,box

    private String type;//颜色 eg color red

    private boolean available;//是否有库存
}
