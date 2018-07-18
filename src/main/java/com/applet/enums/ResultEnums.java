package com.applet.enums;

public enum ResultEnums {

    SERVER_ERROR(500,"系统内部错误"),

    NOT_FOUND_SESSION(404, "错误SESSION"),

    PARAM_IS_NULL(1001,"参数为空"),

    USER_DATA_VALIDATE_FAIL(1002,"用户数据验证失败"),

    REQUEST_RESULT_FAIL(501,"请求结果出错"),

    INVALID_USER(20001,"无效用户"),

    USER_NON_RECHARGE(20002,"用户未充值"),

    SCAVENING_UNLOCK_ORDERNOTFINISH_ERROR(20003,"订单未结束"),

    SCAVENING_UNLOCK_FAILSMSOPENLOCK(20004,"短信开锁失败,请更换其它赳赳单车"),

    SCAVENING_UNLOCK_FAILGPRSOPENLOCK(20005,"GPRS开锁失败,请更换其它赳赳单车"),

    SCAVENING_UNLOCK_LOWBATTERYLEVEL(20006,"此车正在充电,请更换其它赳赳单车"),

    SCAVENING_UNLOCK_BICYCLENONOTFINISH(20007,"车辆不存在"),

    SCAVENING_UNLOCK_ERRORBARCODE(20008,"非法二维码"),

    DATA_NOT_FOUND(20009,"没有门店信息"),

    USER_ALREADY_RECHARGE(20010,"您已经充值过押金"),

    USER_ALREADY_EXIST(1003,"用户已经存在"),

    WX_PLAY_FAIL(1004,"微信支付失败"),

    SCAVENING_UNLOCK_ERRORTRANSRECORD(20008,"订单错误"),

    ELECTRICFANCE_VALIDATION_FAIL(2,"验证失败！"),

    SMS_VALIDATION_FAIL(204,"短信验证码校验失败！"),

    LUCKY_MONEY_ERROR_NO_ACTIVITY(601,"今日无红包活动"),

    LUCKY_MONEY_ERROR_TODAY_DONE(602,"今日已领完"),

    LUCK_MONEY_SUCCESS(200,"请到App中领取"),

    USER_NOT_FOUND(1005,"用户不存在"),

    NOT_FOUND_RANKING_LIST_FAIL(1006,"无排行信息"),

    PHONG_USERD(1007,"手机号已经被绑定");

    private Integer code;

    private String msg;

    ResultEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
}


