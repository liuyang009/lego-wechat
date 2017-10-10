package com.harmontronics.lego.common.response;

import lombok.Data;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-13 14:03
 */
@Data
public class ErrorMsgDto {

    private String errorCode;
    private String errorMessage;
}
