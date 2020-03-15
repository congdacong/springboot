package com.example.aspectj;

import com.example.annotation.Log;
import com.example.entity.SysLog;
import com.example.enums.EnumsConnection;
import com.example.service.SysLogService;
import com.example.util.ServletUtil;
import com.google.gson.Gson;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;

@Component
@Aspect
public class LogAscept {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(LogAscept.class);
    @Autowired
    SysLogService sysLogService;

    /**
     * 配置织入点
     */
    @Pointcut("@annotation(com.example.annotation.Log)")
    public void pointcut() {

    }


    /**
     * 最终通知
     *
     * @param joinPoint
     * @param jsonResult
     */
    @AfterReturning(pointcut = "pointcut()", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, Object jsonResult) {
        handleLog(joinPoint, null, jsonResult);
    }

    /**
     * 异常通知
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, final Exception e, Object jsonResult) {
        try {

            Log controllerLog = getAnnotationLog(joinPoint);
            if (controllerLog == null) {
                return;
            }

            SysLog sysLog = new SysLog();
            sysLog.setStatus(EnumsConnection.BusinessStatus.State.SUCCESS.ordinal());
            sysLog.setOperationUrl(ServletUtil.getRequest().getRequestURI());
            sysLog.setRequestMethod(ServletUtil.getRequest().getMethod());
            System.out.println(jsonResult);
            sysLog.setJsonResult(new Gson().toJson(jsonResult));
            if (e != null) {
                sysLog.setStatus(EnumsConnection.BusinessStatus.State.FAIL.ordinal());
                sysLog.setErrorMessge(e.getMessage().substring( 0, 2000));
            }
            // 设置方法名称
            String className = joinPoint.getTarget().getClass().getName();
            String methodName = joinPoint.getSignature().getName();
            sysLog.setMethod(className + "." + methodName + "()");
            // 处理设置注解上的参数
            getControllerMethodDescription(controllerLog, sysLog);
            sysLogService.save(sysLog);

        } catch (Exception exp) {
            logger.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 获取Controller @Log注解
     *
     * @param log
     * @param sysLog
     * @throws Exception
     */
    public void getControllerMethodDescription(Log log, SysLog sysLog) throws Exception {
        sysLog.setBusinessType(log.businessType().ordinal());
        sysLog.setTitle(log.describe());
    }

    /**
     * 判断@log
     */
    private Log getAnnotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }
}
