package com.lgd.aspect;


import com.lgd.annotation.AddLog;
import com.lgd.dao.LogDao;
import com.lgd.entity.Admin;
import com.lgd.entity.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.UUID;

@Aspect
@Configuration
public class LogAspect {

    @Resource
    private HttpSession session;
    @Resource
    private LogDao logDao;


    @Around("@annotation(com.lgd.annotation.AddLog)")
    public Object addLog(ProceedingJoinPoint proceedingJoinPoint){


        //谁  什么时间    干了什么事     操作是否成功

        //谁
        Admin admin = (Admin) session.getAttribute("admin");
        String username = admin.getUsername();

        //什么时间
        Date date = new Date();


        //干了什么事，操作了哪些方法(获取操作的方法名)
//        String methodName = proceedingJoinPoint.getSignature().getName();

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        String methodName = method.getName();
        AddLog annotation = method.getAnnotation(AddLog.class);
        System.out.println(method);
        System.out.println(annotation);
        String value = annotation.value();

        String message;
        Object proceed = null;

        try {
            proceed = proceedingJoinPoint.proceed();
            message = "success（执行成功）";
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            message = "error(执行失败)";
        }

        Log log = new Log(UUID.randomUUID().toString(), username, date, methodName+"("+value+")", message);
        logDao.insert(log);
        return proceed;
    }

}
