package com.livk.common.core.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.Assert;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * SpringUtils
 * </p>
 *
 * @author livk
 * @date 2022/8/12
 */
@UtilityClass
public class SpringUtils {

    private static final SpelExpressionParser PARSER = new SpelExpressionParser();

    private static final StandardEvaluationContext CONTEXT = new StandardEvaluationContext();

    /**
     * 获取被注解标注的class
     *
     * @param annotationClass annotation
     * @param resourceLoader  resourceLoader
     * @param packages        待扫描的包
     * @return set class
     */
    public Set<Class<?>> findByAnnotationType(Class<? extends Annotation> annotationClass,
                                              ResourceLoader resourceLoader, String... packages) {
        Assert.notNull(annotationClass, "annotation not null");
        Set<Class<?>> classSet = new HashSet<>();
        if (ArrayUtils.isEmpty(packages)) {
            return classSet;
        }
        ResourcePatternResolver resolver = ResourcePatternUtils.getResourcePatternResolver(resourceLoader);
        CachingMetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourceLoader);
        try {
            for (String packageStr : packages) {
                packageStr = packageStr.replace(".", "/");
                Resource[] resources = resolver.getResources("classpath*:" + packageStr + "/**/*.class");
                for (Resource resource : resources) {
                    MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                    String className = metadataReader.getClassMetadata().getClassName();
                    Class<?> clazz = Class.forName(className);
                    if (AnnotationUtils.findAnnotation(clazz, annotationClass) != null) {
                        classSet.add(clazz);
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return classSet;
    }

    /**
     * 解析SpEL表达式
     *
     * @param method      方法
     * @param args        参数
     * @param condition   表达式
     * @param returnClass 返回类型
     * @param <T>         返回类型
     * @return t
     */
    public <T> T parseSpEL(Method method, Object[] args, String condition, Class<T> returnClass) {
        var discoverer = new LocalVariableTableParameterNameDiscoverer();
        var parameterNames = discoverer.getParameterNames(method);
        Assert.notNull(parameterNames, "参数列表不能为null");
        for (int i = 0; i < parameterNames.length; i++) {
            CONTEXT.setVariable(parameterNames[i], args[i]);
        }
        return PARSER.parseExpression(condition).getValue(CONTEXT, returnClass);
    }

    public String parseSpEL(Method method, Object[] args, String condition) {
        return parseSpEL(method, args, condition, String.class);
    }

    public <T> T parseSpEL(Map<String, ?> variables, String condition, Class<T> returnClass) {
        variables.forEach(CONTEXT::setVariable);
        return PARSER.parseExpression(condition).getValue(CONTEXT, returnClass);
    }

    public String parseSpEL(Map<String, ?> variables, String condition) {
        return parseSpEL(variables, condition, String.class);
    }

}
