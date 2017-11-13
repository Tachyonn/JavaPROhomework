package JavaPROsrc.lesson01Task2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) {
        TextContainer tc = new TextContainer();
        Class<?> cls = TextContainer.class;
        SaveTo saveTo = cls.getAnnotation(SaveTo.class);
        String path = saveTo.path();
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Saver.class)) {
                try {
                    method.invoke(tc, path);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
