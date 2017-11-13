package JavaPROsrc.lesson01Task1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        Class<?> cls = TestClassOne.class;
        Method[] methods = cls.getDeclaredMethods();
        for (Method meth : methods) {
            if (meth.isAnnotationPresent(MyAnnot.class)) {
                MyAnnot an = meth.getAnnotation(MyAnnot.class);
                try {
                    meth.invoke(null,an.a(), an.b());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
