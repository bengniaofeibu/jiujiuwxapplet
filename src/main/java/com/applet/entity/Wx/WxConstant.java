package com.applet.entity.Wx;


public abstract class WxConstant {


    public static final String APP_ID="wx53f75659095ed05f";

    public static final String SECRET="dd970dc232613afba5809c0857f4df07";

//    public static final String APP_ID="wx20e2943654ed0326";
//
//    public static final String SECRET="ac2275cf0996a28e746c3fa16cdbcd40";

    public static final String GET_TOKEN = "https://api.weixin.qq.com/cgi-bin/token";

    public static final String GET_USER_INFO= "https://api.weixin.qq.com/cgi-bin/user/info";

    public static final String SEND_WX_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    public static final String SEND_WX_TEST = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";

}
