package com.applet.controller.LockController;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.applet.annotation.SystemControllerLog;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017-08-18.
 */
@RestController
@RequestMapping(value = "/ossutil")
public class OssObjectPolicy {

    public static final  String OSS_ENDPOINT = "oss-cn-shanghai.aliyuncs.com";
    public static final String OSS_ACCESSID = "LTAIXtzzxKiWP3By";
    public static final String OSS_ACCESSKEY = "S0AhvjObf3DlKjIsfgY5UNgnTrBMTZ";
    public static final String OSS_ACCESS_SECRET="S0AhvjObf3DlKjIsfgY5UNgnTrBMTZ";
    public static final String OSS_BUCKET = "jjdc-client";
    public static final String OSS_COMM_DIR = "applet";

   @SystemControllerLog(funcionExplain = "进入获取osskey控制层")
   @GetMapping(value = "/wx_xcx_policy")
    public  Map  getOssPolicy(HttpServletRequest request){
        //前端用户提交图片的文件夹
        String user_dir=request.getParameter("user_dir");
        if(StringUtils.isBlank(user_dir)){
            user_dir=OSS_COMM_DIR;
        }
        String endpoint =OSS_ENDPOINT;
        String accessId =OSS_ACCESSID;
        String accessKey = OSS_ACCESSKEY;
        String bucket = OSS_BUCKET;
        String dir = user_dir;
        String host = "https://" + bucket + "." + endpoint;
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        long expireTime = 30;
        long expireEndTime = System.currentTimeMillis() + expireTime *60*1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
        String postPolicy = client.generatePostPolicy(expiration, policyConds);
        String encodedPolicy = new String(Base64.encodeBase64(postPolicy.getBytes()));
        String postSignature = client.calculatePostSignature(postPolicy);
        Map<String, String> respMap = new LinkedHashMap<String, String>();
        respMap.put("accessid", accessId);
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        respMap.put("dir", dir);
        respMap.put("host", host)   ;
        return respMap;
    }
}




