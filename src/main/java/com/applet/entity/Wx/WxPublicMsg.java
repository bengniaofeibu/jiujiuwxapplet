package com.applet.entity.Wx;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WxPublicMsg {

    private String toUserName;

    private String fromUserName;

    private String createTime;

    private Integer msgType;

    private String event;

    private String eventKey;
}
