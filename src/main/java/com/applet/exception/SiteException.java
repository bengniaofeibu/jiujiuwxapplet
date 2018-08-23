package com.applet.exception;


import com.applet.enums.ResultEnums;

public class SiteException extends RuntimeException {
    private Integer code;

    public Integer getCode() {
        return code;
    }

    void setCode(Integer code) {
        this.code = code;
    }

    public SiteException(ResultEnums resultEnum) {
        super(resultEnum.getMsg());
        this.setCode(resultEnum.getCode());
    }
}
