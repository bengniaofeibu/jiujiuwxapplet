package com.applet.entity.Wx;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WxPublicText {

    public static final int JIUMI_NUM = 260;

    private static final String MSG_TYPE = "text";

    private static final String TEXT_CONTENT = "完成公众号任务，加"+JIUMI_NUM+"赳米";

    private static final TextContent CONTENT = new TextContent();

    private String touser;

    private String msgtype = MSG_TYPE;

    private TextContent text = CONTENT;

    public WxPublicText(String touser) {
        this.touser = touser;
    }

    @Getter
    @Setter
    static class  TextContent{
        private String content = TEXT_CONTENT;
    }
}
