package org.example;


import com.google.auto.service.AutoService;
import com.sun.source.util.Trees;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Names;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.Set;


// Google에서 제작한 AutoService를 사용하여
// 이 GetterProcessor를 Processor로 등록해달라고 하는 것이다.
@AutoService(Processor.class)
@SupportedAnnotationTypes("org.example.JeongGetter")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class GetterProcessor extends AbstractProcessor {
    // implements Processor를 구현하여도 되지만
    // AbstractProcessor가 Processor를 어느정도 구현 해놓았다.

    private ProcessingEnvironment processingEnvironment;
    private Trees trees;
    private TreeMaker treeMaker;
    private Names names;
    private Context context;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.processingEnvironment = processingEnv;
//        this.trees = Trees.instance(processingEnv);
//        this.context = javacProcessingEnvironment.getContext();
//        this.treeMaker = TreeMaker.instance(context);
//        this.names = Names.instance(context);
    }

    // ture를 리턴 시 이 어노테이션의 타입을 처리하였으니 (여러 라운드의) 다음 프로세서들은 이 어노테이션의 타입을 다시 처리하진 않는다.
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(JeongGetter.class);
        return true;
    }

}