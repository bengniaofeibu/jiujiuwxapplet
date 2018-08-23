package com.applet.entity.Wx;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WxToken extends WxError{

    private String access_token;

    private Integer expires_in;

}
