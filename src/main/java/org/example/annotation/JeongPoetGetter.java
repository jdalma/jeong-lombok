package org.example.annotation;

import java.lang.annotation.*;

/**
 * JeongEntity
 * 1. 인터페이스에만 선언 가능하며 해당 인터페이스의 구현체를 생성한다.
 * 2. 인터페이스에 선언 시 아래와 같이 메서드들이 제공된다.
 *   - Getter
 *   - Setter
 *   - toString
 *   - equals
 *   - Constructor
 *     - full field
 *     - map
 * @author jeongcode
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface JeongPoetGetter {
}
