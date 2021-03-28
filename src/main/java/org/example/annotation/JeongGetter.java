package org.example.annotation;

import java.lang.annotation.*;

/**
 * JeongGetter
 * 1. 해당 어노테이션은 컴파일러 내부 클래스를 사용하여 AST를 조작하는 방법의 예시를 보여주는 목적이다.
 * @author jeongcode
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface JeongGetter {
}
