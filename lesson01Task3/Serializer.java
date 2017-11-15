package JavaPROsrc.lesson01Task3;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class Serializer {
    private final String basePath = "src/JavaPROsrc/lesson01Task3/";
    private final String prefix = "sz_";
    private final String fileExtension = ".txt";

    public void serialize(Object tInstance) {
        String filename = basePath + prefix + tInstance.getClass().getName() + fileExtension;
        try {
            saveProperties(writeObject(tInstance), filename);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public <T> T deserialize(Class<T> tClass) {
        String filename = basePath + prefix + tClass.getName() + fileExtension;
        try {
            return readObject(loadProperties(filename), tClass);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        throw new UnsupportedOperationException("Deserialization Failure!");
    }

    private Properties writeObject(Object tInstance) throws IllegalAccessException {
        if (tInstance == null) {
            throw new IllegalArgumentException("Argument is Null!");
        }
        Properties savedClassFields = new Properties();
        Class<?> classRef = tInstance.getClass();
        //savedClassFields.put("class", classRef.toString());
        Field[] fields = classRef.getDeclaredFields();
        for (Field field : fields) {
            if (!isAnnotated(field, Save.class)) {
                continue;
            }
            String[] properties = getProperties(tInstance, field);
            savedClassFields.put(properties[0], properties[1]);
        }
        return savedClassFields;
    }

    private <T> T readObject(Properties properties, Class<T> tClass) throws IllegalAccessException,
            InstantiationException, NoSuchFieldException {
        if (properties == null || tClass == null) {
            throw new IllegalArgumentException("Argument is Null!");
        }
        T tInstance = tClass.newInstance();
        List<String> keys = new ArrayList<>(properties.stringPropertyNames());
        for (String key : keys) {
            String value = properties.getProperty(key);
            Field field = tClass.getDeclaredField(key);
            setProperties(tInstance, field, value);
        }
        return tInstance;
    }

    private boolean isAnnotated(Field field, Class<? extends Annotation> annotation) {
        return field.isAnnotationPresent(annotation);
    }

    private String[] getProperties(Object o, Field field) throws IllegalAccessException {
        String[] properties = new String[2];
        if (Modifier.isPrivate(field.getModifiers())) {
            field.setAccessible(true);
        }
        properties[0] = field.getName();
        if (field.getType() == int.class) {
            properties[1] = "" + field.getInt(o);
        } else if (field.getType() == String.class) {
            properties[1] = "" + field.get(o);
        } else if (field.getType() == Long.class) {
            properties[1] = "" + field.getLong(o);
        } else if (field.getType() == boolean.class) {
            properties[1] = "" + field.getBoolean(o);
        } else if (field.getType() == char.class) {
            properties[1] = "" + field.getChar(o);
        }
        return properties;
    }

    private <T> void setProperties(T tInstance, Field field, String value) throws IllegalAccessException {
        if (Modifier.isPrivate(field.getModifiers())) {
            field.setAccessible(true);
        }
        if (field.getType() == int.class) {
            field.setInt(tInstance, Integer.parseInt(value));
        } else if (field.getType() == String.class) {
            field.set(tInstance, value);
        } else if (field.getType() == Long.class) {
            field.setLong(tInstance, Long.parseLong(value));
        } else if (field.getType() == boolean.class) {
            field.setBoolean(tInstance, Boolean.parseBoolean(value));
        } else if (field.getType() == char.class) {
            field.setChar(tInstance, value.charAt(0));
        }
    }

    private boolean saveProperties(Properties properties, String filename) {
        if (Objects.isNull(properties) || Objects.isNull(filename)) {
            throw new IllegalArgumentException("Arguments is Null!");
        }
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (OutputStream os = new FileOutputStream(file)) {
            properties.store(os, "");
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private Properties loadProperties(String filename) {
        if (Objects.isNull(filename)) {
            throw new IllegalArgumentException("Arguments is Null!");
        }
        Properties properties = new Properties();
        File file = new File(filename);
        try (InputStream is = new FileInputStream(file)) {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }


}
