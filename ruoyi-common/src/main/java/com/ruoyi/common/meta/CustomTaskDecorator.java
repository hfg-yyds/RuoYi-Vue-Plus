package com.ruoyi.common.meta;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.task.TaskDecorator;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * <p>
 *  装饰器设计模式 在线程中传递上下文
 * </p>
 *
 * @author: 韩福贵
 * @date: 2023-02-04
 */
@Slf4j
public class CustomTaskDecorator implements TaskDecorator {

    @NotNull
    @Override
    public Runnable decorate(@NotNull Runnable runnable) {
        try {
            //获取父线程的request的user-agent(示例)
            HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
            String  ua = request.getHeader("user-agent");
            return ()->{
                try {
                    //TODO 将ua设置到线程副本当中去
                    runnable.run();
                }catch (Exception e) {
                    log.error("错误信息:{},堆栈信息:{}",e.getMessage(),e);
                }
            };
        }catch (Exception e) {
            log.error("错误信息:{},堆栈信息:{}",e.getMessage(),e);
            return runnable;
        }
    }

}