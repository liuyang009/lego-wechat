package com.harmontronics.lego.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Soul on 2017/7/26.
 */
@Data
public class SimpleMaterialDto implements Serializable {
    private static final long serialVersionUID = 6891545872446585130L;

    private Long id;
    private String name;
    private String type;
    private Boolean available;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
