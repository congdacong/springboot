package com.example.annotation;

import com.example.enums.EnumsConnection;

import java.lang.annotation.*;

@Target(ElementType.METHOD) //注解放置的目标位置,METHOD是可注解在方法级别上
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented //生成文档
public @interface Log {
    String describe() default "";//操作简述
    EnumsConnection.BusinessType.State businessType() default EnumsConnection.BusinessType.State.OTHER;//操作类型



}
