package by.mark.potato.context;

import by.mark.potato.annotation.Potato;
import by.mark.potato.exception.PotatoException;
import by.mark.potato.runner.PotatoItemRunner;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;

public class PotatoApplication {

    static final Set<Class<?>> POTATO_CLASSES = ConcurrentHashMap.newKeySet();
    static final ConcurrentMap<String, Object> POTATOES = new ConcurrentHashMap<>();

    public static void run(Class<?> clazz) {
        List<Class<?>> potentialPotatoClasses = PotatoItemRunner.findClasses(clazz.getPackageName());
        loadPotatoes(potentialPotatoClasses);
        plantPotatoes();
        System.out.println("All potatoes has been plant");
    }

    private static void loadPotatoes(List<Class<?>> potentialPotatoClasses) {
        try {
            for (Class<?> foundedClazz : potentialPotatoClasses) {
                if (!foundedClazz.isAnnotationPresent(Potato.class)) {
                    continue;
                }

                Potato potato = foundedClazz.getAnnotation(Potato.class);
                Object instance = foundedClazz.getDeclaredConstructors()[0].newInstance();
                POTATOES.put(potato.name(), instance);
                POTATO_CLASSES.add(foundedClazz);
            }
        } catch (Exception e) {
            throw new PotatoException("Something went wrong", e);
        }
    }

    private static void plantPotatoes() {
        POTATO_CLASSES.forEach(clazz ->
                Stream.of(clazz.getDeclaredFields())
                        .filter(field -> POTATOES.containsKey(field.getName()))
                        .forEach(field -> injectPotato(clazz, field))
        );
    }

    private static void injectPotato(Class<?> clazz, Field field) {
        try {
            field.setAccessible(true);
            Object potatoInstance = POTATOES.get(clazz.getAnnotation(Potato.class).name());
            field.set(potatoInstance, POTATOES.get(field.getName()));
        } catch (IllegalAccessException e) {
            throw new PotatoException("Something went wrong", e);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getPotato(String name) {
        return (T) Objects.requireNonNull(PotatoApplication.POTATOES.get(name), "Potato can not be found");
    }

    @SuppressWarnings("unchecked")
    public static <T> T findPotato(String name) {
        return (T) PotatoApplication.POTATOES.get(name);
    }
}
