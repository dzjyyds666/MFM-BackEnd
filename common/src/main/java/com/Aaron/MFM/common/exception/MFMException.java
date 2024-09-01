package com.Aaron.MFM.common.exception;

import com.Aaron.MFM.common.result.ResultCodeEnum;
import lombok.Data;

@Data
public class MFMException extends RuntimeException {

    private Integer code;
    public MFMException(Integer code,String message) {
        super(message);
        this.code = code;
    }

    public MFMException(ResultCodeEnum resultCodeEnum){
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }
}
