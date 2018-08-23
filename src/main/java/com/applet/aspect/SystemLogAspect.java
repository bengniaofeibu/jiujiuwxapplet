package com.applet.aspect;

import com.applet.annotation.SystemControllerLog;
import com.applet.annotation.SystemServerLog;
import com.applet.utils.ApiManager;
import com.applet.utils.common.JSONUtil;
import jiujiu.Head.ApiHead;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;


@Component
@Aspect
public class SystemLogAspect {


    private static final Logger LOGGER= LoggerFactory.getLogger(SystemLogAspect.class);

    @Autowired
    private HttpServletRequest request;

    private ThreadLocal<Date> threadLocal=new NamedThreadLocal<>("threadLocal time");

    private static final SimpleDateFormat DATE_FORMAT=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    private static final String POST_METHOD ="POST";

    private static final String GET_METHOD ="GET";


    @Pointcut("@annotation(com.applet.annotation.SystemServerLog)")
    private void serverAspect(){}

    @Pointcut("@annotation(com.applet.annotation.SystemControllerLog)")
    private void controllerAspect(){}

    @Before("controllerAspect()")
    public void before(JoinPoint joinPoint)throws Exception{

        //签名认证
        ApiHead head = ApiManager.getApiHead(request);

        ApiManager.valideHeadApi(head);

        Date date=new Date();
        threadLocal.set(date);
        if (LOGGER.isInfoEnabled()){
            try {
                LOGGER.info("{},{},{},{},{} ",DATE_FORMAT.format(date),getControllerMthodDescription(joinPoint),request.getRemoteAddr(),request.getMethod(),getRequestParam(joinPoint.getArgs(),request.getMethod(),request.getParameterMap()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @After("serverAspect()")
    public void doAfter(JoinPoint joinPoint){
        try {
            String methodName = joinPoint.getSignature().getName();
            //开始之前
            long startTime=threadLocal.get().getTime();
            long endTime=System.currentTimeMillis();
            if (LOGGER.isDebugEnabled()){
                LOGGER.debug("{}--{}方法执行时间 {}ms",getServiceMthodDescription(joinPoint),methodName,(endTime-startTime));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterReturning(returning = "object", pointcut = "serverAspect()")
    public void doAfterReturning(Object object) {
        if (LOGGER.isDebugEnabled()){
            LOGGER.debug("response={}", JSONUtil.toJSONString(object));
        }
    }


    @AfterThrowing(value = "serverAspect()",throwing = "exception")
    public  void doAfterThrowing(Throwable exception){
        if (LOGGER.isErrorEnabled()){
           LOGGER.error(" ERROR {}" ,exception.getMessage());
        }
    }


    /**
     * 获取注解中对方法的描述信息 用于service层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public  static String getServiceMthodDescription(JoinPoint joinPoint)
            throws Exception {
       MethodSignature methodSignature= (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        SystemServerLog annotation = method.getAnnotation(SystemServerLog.class);
        return annotation.funcionExplain();
    }

    /**
     * 获取注解中对方法的描述信息 用于controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public  static String getControllerMthodDescription(JoinPoint joinPoint)
            throws Exception {
        MethodSignature methodSignature= (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        SystemControllerLog annotation = method.getAnnotation(SystemControllerLog.class);
        return method.getName();
    }

    /**
     * 获取请求参数
     * @param params
     */
    public String getRequestParam(Map<String,String[]> params ){

        StringBuilder builder=new StringBuilder();

        for (Map.Entry<String,String[]> entry:params.entrySet()){
            builder.append(JSONUtil.toJSONString(entry.getValue())).append(" ");
        }
        return builder.toString();
    }

    /**
     * 获取请求参数
     * @param objects
     * @param method
     */
    public String getRequestParam(Object[] objects,String method,Map<String,String[]> params ){

        String res = null;
         switch (method){
             case POST_METHOD:
              res = JSONUtil.toJSONString(objects[0]);
             break;
             case GET_METHOD:
              res = getRequestParam(params);
             break;
         }
         return res;
    }
}


