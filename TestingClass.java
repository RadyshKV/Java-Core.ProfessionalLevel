package HomeWork7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestingClass {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        start(TestClass1.class);
        start("HomeWork7.TestClass2");
    }

    public static void start(Class<?> testClass) throws IllegalAccessException, InstantiationException, InvocationTargetException {

        Object classObject = testClass.newInstance();
        Map<Integer, Set<Method>> testMethodsMap = new TreeMap();
        Method beforeSuiteMethod = null;
        Method afterSuiteMethod = null;

        Method[] methods = testClass.getDeclaredMethods();

        for (Method method : methods) {

            if (method.isAnnotationPresent(Test.class)) {
                Test testAnnotation = method.getAnnotation(Test.class);
                Set<Method> methodSet = testMethodsMap.getOrDefault(testAnnotation.priority(), new HashSet<>());
                methodSet.add(method);
                testMethodsMap.put(testAnnotation.priority(), methodSet);
            }

            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeSuiteMethod == null) {
                    beforeSuiteMethod = method;
                } else {
                    throw new RuntimeException();
                }
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                if (afterSuiteMethod == null) {
                    afterSuiteMethod = method;
                } else {
                    throw new RuntimeException();
                }
            }
        }

        if (beforeSuiteMethod != null) {
            beforeSuiteMethod.invoke(classObject);
        }
        for (Integer priority : testMethodsMap.keySet()) {
            Set<Method> methodSet = testMethodsMap.get(priority);
            for (Method method : methodSet) {
                method.invoke(classObject);
            }
        }
        if (afterSuiteMethod != null) {
            afterSuiteMethod.invoke(classObject);
        }
    }


    public static void start(String className) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class testClass = Class.forName(className);
        start(testClass);
    }


}
