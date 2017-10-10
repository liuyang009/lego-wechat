package com.harmontronics.lego.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-13 13:57
 */
@Data
public class ResponseDto<T> implements Serializable {

    private boolean success;
    private T data;
}
