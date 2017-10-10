package com.harmontronics.lego.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Soul on 2017/7/26.
 */
@Data
public class SimpleDeviceDto implements Serializable {
    private static final long serialVersionUID = -2633838262209816649L;

    private Long id;
    private String name;
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
