package org.example.annotation;

import java.lang.annotation.*;

/**
 * JeongEntity
 * 1. 클래스 레벨에만 작성 허용한다.
 * 2. Getter , Setter , toString , equals , Constructor를 생성한다.
 * @author jeongcode
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface JeongEntity {
}
