package com.applet.entity.Wx;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class WxError {

    private Integer errcode;

    private String errmsg;
}
