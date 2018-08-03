package com.applet.entity.Wx;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DataValue {

    public static final String FIRST_DATA = "赳赳单车";

    public static final String JIU_MI_REMARK_DES = "恭喜你获得50个赳米";

    private static final String DATA_COLOR = "#173177";

    private String value;

    private String color = DATA_COLOR;

    public DataValue(String value) {
        this.value = value;
    }
}
