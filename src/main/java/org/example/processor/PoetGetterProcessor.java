package org.example.processor;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.*;
import org.example.annotation.JeongPoetGetter;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.*;

@AutoService(Processor.class)
@SupportedAnnotationTypes("org.example.annotation.JeongPoetGetter")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class PoetGetterProcessor extends AbstractProcessor {

    // ture를 리턴 시 이 어노테이션의 타입을 처리하였으니 (여러 라운드의) 다음 프로세서들은 이 어노테이션의 타입을 다시 처리하진 않는다.
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(JeongPoetGetter.class);

        // @JeongPoetGetter 어노테이션이 적용된 클래스,인터페이스.. 가져오기
        for(Element element : elements){
            Name elementName = element.getSimpleName();
            if(element.getKind() != ElementKind.CLASS){
                // JeongPoetGetter 어노테이션을 클래스가 아닌 다른 곳에 작성하였다면
                // 컴파일 에러 처리
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR , "@JeongPoetGetter annotation cant be used on" + element.getSimpleName());
            }
            else{
                // 클래스에 제대로 작성하였다면
                // 로깅
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE , "@JeongPoetGetter annotation Processing " + element.getSimpleName());
            }

            // javax.lang.model.element public interface TypeElement를
            // com.squareup.javapoet public final class ClassName 으로 변환할 수 있다.
            TypeElement typeElement = (TypeElement) element;
            // ClassName으로 그 클래스에 대한 정보를 사용할 수 있다.
            ClassName className = ClassName.get(typeElement);

            // 해당 클래스의 필드 가져오기
            List<MethodSpec> methodSpecArr = new ArrayList<MethodSpec>();
            for(Element field : element.getEnclosedElements()){
                if(field.getKind() == ElementKind.FIELD){
                    String fieldName = field.getSimpleName().toString();
                    String upperName = fieldName.substring(0,1).toUpperCase() + fieldName.substring(1 , fieldName.length());
                    // com.squareup.javapoet public final class MethodSpec을 사용하여 메서드를 만들 수 있다.
                    System.out.println(field.asType());
                    try {
                        MethodSpec getter = MethodSpec.methodBuilder("get" + upperName)
                                .addModifiers(Modifier.PUBLIC)
                                // 참조형 타입일 시 Class.forName , 프리미티브 타입일 시 getType 호출
                                .returns(("DECLARED".equals(field.asType().getKind().toString())) ? Class.forName(field.asType().toString()) : getType(field.asType().getKind()))
                                .addStatement("return super." + fieldName)
                                .build();
                        methodSpecArr.add(getter);
                    } catch (ClassNotFoundException e) {
                        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR , "ClassNotFoundException : " + e);
                    }
                }
            }

            // com.squareup.javapoet.TypeSpec을 사용하여 클래스를 만들 수 있다.
            // classBuilder의 addMethods(Iterable<MethodSpec> methodSpecs)
            TypeSpec makeClass = TypeSpec.classBuilder(elementName + "Entity")
                    .addModifiers(Modifier.PUBLIC)
                    .superclass(className)
                    .addMethods(methodSpecArr)
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
            } catch (Exception e) {
                processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR , "FATA ERROR : " + e);
            }
        }
        return true;
    }

    // 프리미티브 타입일 때
    private Class getType(TypeKind kind){
        switch(kind) {
            case BOOLEAN:
                return boolean.class;
            case BYTE:
                return byte.class;
            case SHORT:
                return short.class;
            case INT:
                return int.class;
            case LONG:
                return long.class;
            case CHAR:
                return char.class;
            case FLOAT:
                return float.class;
            case DOUBLE:
                return double.class;
            default:
                throw new AssertionError();
        }
    }
}
