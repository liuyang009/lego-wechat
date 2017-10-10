package com.harmontronics.lego.common.response;

/**
 * ${DESCRIPTION}
 *
 * @author Five.Liu
 * @create 2017-07-13 13:59
 */
public class ResponseDtoFactory {


    /**
     * 返回前端需要的数据
     * @param success
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseDto<T> getResponseDto(boolean success,T data){
        ResponseDto<T> responseBean = new ResponseDto<>();
        responseBean.setSuccess(success);
        responseBean.setData(data);
        return responseBean;
    }

    /**
     * 返回成功的数据格式
     * @param msg
     * @return
     */
    public static ResponseDto<SuccessMsgDto> getSuccessResponseDto(String msg){
        SuccessMsgDto successBean = new SuccessMsgDto();
        successBean.setMessage(msg);
        return getResponseDto(true, successBean);
    }

    /**
     * 返回错误的数据格式
     * @param code
     * @param msg
     * @return
     */
    public static ResponseDto<ErrorMsgDto> getErrorResponseDto(String code, String msg){
        ErrorMsgDto errorBean = new ErrorMsgDto();
        errorBean.setErrorMessage(msg);
        errorBean.setErrorCode(code);
        return getResponseDto(false, errorBean);
    }
}
