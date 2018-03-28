package com.applet.utils;

import com.applet.enums.ResultEnums;
import org.springframework.stereotype.Component;

@Component
public class ResultUtil {
    public static AppletResult success(Object object){

        AppletResult result = new AppletResult();
        result.setCode(200);
        result.setMsg("success");
        result.setData(object);
        return result;
    }

    public static AppletResult success(){
        AppletResult result = new AppletResult();
        result.setCode(200);
        result.setMsg("success");
        return result;
    }


    public static AppletResult error(ResultEnums resultEnums,String...msg){
        AppletResult result = new AppletResult();
        result.setCode(resultEnums.getCode());
        if (msg.length>0){
            StringBuffer sb=new StringBuffer(msg[0]);
            result.setMsg(sb.append(resultEnums.getMsg()).toString());
        }else {
            result.setMsg(resultEnums.getMsg());
        }
        return result;
    }

    public static AppletResult error(Object i,String msg){
        AppletResult result = new AppletResult();
        result.setCode(i);
        result.setMsg(msg);
        return result;
    }

}
