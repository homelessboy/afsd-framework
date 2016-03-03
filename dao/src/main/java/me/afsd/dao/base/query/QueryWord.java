package me.afsd.dao.base.query;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * User: XuHui
 * Date: 2016/2/29
 * Time: 17:48
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface QueryWord {

    String value() default "";

    MatchType matchType() default MatchType.eq;

    boolean searchNull() default false;

    Class enumType() default Enum.class;
}
