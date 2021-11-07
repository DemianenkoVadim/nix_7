package ua.com.alevel.factory;

import lombok.extern.log4j.Log4j2;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.Set;

@Log4j2
public class ObjectFactory {

    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();
    private final Reflections reflections = new Reflections("ua.com.alevel");

    private ObjectFactory() {
    }

    public static ObjectFactory getInstance() {
        return OBJECT_FACTORY;
    }

    public static <T> T getClass(Class<T> tClass) {
        return OBJECT_FACTORY.getImplClass(tClass);
    }

    public <T> T getImplClass(Class<T> tClass) {
        Set<Class<? extends T>> objects = reflections.getSubTypesOf(tClass);
        if (objects.size() > 1) {
            throw new RuntimeException("More than one impl found");
        }
        for (Class<?> object : objects) {
            try {
                return (T) object.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        throw new RuntimeException("Impl not found");
    }
}
