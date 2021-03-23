package org.example;

import java.lang.annotation.*;

/**
 * Jeong-Lombok : Getter
 * 1. 클래스에만 선언 가능하다.
 * 2. 클래스에 선언 시 내부 필드를 모두 인식하여 getter 메서드를 생성하여 준다.
 * @author jeongcode
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface JeongGetter {

}
