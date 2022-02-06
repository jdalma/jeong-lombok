# jeong-lombok
**나만의 롬복 만들기**
- `@JeongGetter` : AST 예제
- `@JeongPoetGetter` : JavaPoet 예제
- `@JeongEntity`
  - Getter
  - Setter

# **✍ 키워드**

📌 **[Lombok은 어떻게 동작하는걸까? (AnnotationProcessor에 대해)](https://jeongcode.github.io/docs/java/Annotation%20Processor/)**

📌 **[Java 컴파일러](https://jeongcode.github.io/docs/java/javac-principle/)**

- javax.annotation.processing
  - AbstractProcessor , **[Processor](https://docs.oracle.com/javase/8/docs/api/javax/annotation/processing/Processor.html)**
  - **[Filer 인터페이스](https://docs.oracle.com/en/java/javase/11/docs/api/java.compiler/javax/annotation/processing/Filer.html)**
  - RoundEnvironment
  - ProcessingEnvironment
- javax.lang.model
  - Element
  - TypeElement
-  **[Javapoet](https://github.com/square/javapoet)**
-  **[AutoService](https://github.com/google/auto/tree/master/service)**
