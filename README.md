# [**나만의 롬복 만들기**](https://jdalma.github.io/docs/toy-project/Jeong-Lombok/#jeong-lombok-github)

JavaPoet , AnnotationProcessor , Reflection , 자바컴파일러 의 이해를 위한 프로젝트입니다.

- `@JeongGetter` : AST 예제
- `@JeongPoetGetter` : JavaPoet 예제

# **키워드**

📌 **[Lombok은 어떻게 동작하는걸까? (AnnotationProcessor에 대해)](https://jdalma.github.io/docs/java/Annotation%20Processor/)**

📌 **[Java 컴파일러](https://jdalma.github.io/docs/java/javac-principle/)**

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
