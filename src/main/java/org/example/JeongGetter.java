package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 인터페이스 , 클래스 , 이늄에 붙일 수 있다.
@Target(ElementType.TYPE)
// 컴파일 타임에 사용하고 바이트코드에서는 필요없다.
@Retention(RetentionPolicy.SOURCE)
public @interface JeongGetter {

}
