package me.afsd.dao.base.query;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author  XuHui (416422546@qq.com)
 * @version 0.0.1
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryWord {

    String value() default "";

    MatchType matchType() default MatchType.eq;

    boolean searchNull() default false;

    Class enumType() default Enum.class;
}
