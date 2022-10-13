package org.example.annotation;

import java.lang.annotation.*;

/**
 * 인터페이스에만 선언 가능하며 해당 인터페이스의 구현체를 생성한다.
 * @author jeongcode
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface JeongPoetGetter {
}
