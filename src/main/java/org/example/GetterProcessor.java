package org.example;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Set;

// Google에서 제작한 AutoService를 사용하여
// 이 GetterProcessor를 Processor로 등록해달라고 하는 것이다.
@AutoService(Processor.class)
public class GetterProcessor extends AbstractProcessor {
    // implements Processor를 구현하여도 되지만
    // AbstractProcessor가 Processor를 어느정도 구현 해놓았다.

    // 이 프로세서가 어떤 어노테이션을 처리할 것인지
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        // 처리할 어노테이션의 문자열
        return Set.of(JeongGetter.class.getName());
    }

    // 어떤 소스코드의 버전을 지원하는지
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    // ture를 리턴 시 이 어노테이션의 타입을 처리하였으니 (여러 라운드의) 다음 프로세서들은 이 어노테이션의 타입을 다시 처리하진 않는다.
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(JeongGetter.class);

        for(Element element : elements){
            Name elementName = element.getSimpleName();
            if(element.getKind() != ElementKind.INTERFACE){
                // JeongGetter 어노테이션을 인터페이스가 아닌 다른 곳에 작성하였다면
                // 컴파일 에러 처리
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR , "Getter Annotation can not used on " + elementName);
            }
            else{
                // 인터페이스에 제대로 작성하였다면
                // 로깅
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE , "Processing " + elementName);
            }

            // javax.lang.model.element public interface TypeElement를
            // com.squareup.javapoet public final class ClassName 으로 변환할 수 있다.
            TypeElement typeElement = (TypeElement) element;
            // ClassName으로 그 클래스에 대한 정보를 사용할 수 있다.
            ClassName className = ClassName.get(typeElement);

            // com.squareup.javapoet public final class MethodSpec을 사용하여 메서드를 만들 수 있다.
            MethodSpec get = MethodSpec.methodBuilder("get")
                                        .addModifiers(Modifier.PUBLIC)
                                        .returns(String.class)
                                        .addStatement("return $S" , "getter")
                                        .build();

            // com.squareup.javapoet.TypeSpec을 사용하여 클래스를 만들 수 있다.
            TypeSpec makeClass = TypeSpec.classBuilder("Getter")
                                        .addModifiers(Modifier.PUBLIC)
                                        .addSuperinterface(className)
                                        .addMethod(get)
                                        .build();

            // 위에서 정의한 Spec을 사용하여 실제 Source 코드에 삽입해보자

            // 1. javax.annotation.processing public interface Filer 인터페이스를 가져오자
            Filer filer = processingEnv.getFiler();

            // 2. com.squareup.javapoet public final class JavaFile 을 사용
            // 2.1 makeClass를 해당 패키지에 만들어 달라.
            // 2.2 위에서 가저온 filer를 사용하여 써달라.
            try {
                JavaFile.builder(className.packageName() , makeClass)
                        .build()
                        .writeTo(filer);
            } catch (IOException e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR , "FATA ERROR : " + e);
            }
        }
        return true;
    }

}