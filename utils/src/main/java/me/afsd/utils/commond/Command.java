package me.afsd.utils.commond;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author XuHui (416422546@qq.com)
 * @version 0.0.1
 * @date 2016/12/22
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {
    String command() default "";

    String desc() default "";

    boolean rangArgs() default false;//参数是否为数组 默认不是
}
