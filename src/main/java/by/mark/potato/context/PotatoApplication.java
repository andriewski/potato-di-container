package by.mark.potato.context;

import by.mark.potato.annotation.Potato;
import by.mark.potato.runner.PotatoItemRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class PotatoApplication {

    static final Set<Class<?>> POTATO_CLASSES = new HashSet<>();
    static final HashMap<String, Object> POTATOES = new HashMap<>();

    public static void run(Class<?> clazz) {
        List<Class<?>> potentialPotatoClasses;
        try {
            potentialPotatoClasses = PotatoItemRunner.getClasses(clazz.getPackageName());
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException("Potential potato classes can not be loaded", e);
        }
        loadPotatoes(potentialPotatoClasses);
        plantPotatoes();
        System.out.println("All potatoes has been plant");
    }

    private static void loadPotatoes(List<Class<?>> potentialPotatoClasses) {
        try {
            for (Class<?> foundedClazz : potentialPotatoClasses) {
                if (foundedClazz.isAnnotationPresent(Potato.class)) {
                    Potato potato = foundedClazz.getAnnotation(Potato.class);
                    Object instance = foundedClazz.getDeclaredConstructors()[0].newInstance();
                    POTATOES.put(potato.name(), instance);
                    POTATO_CLASSES.add(foundedClazz);
                }
            }
        } catch (Exception e) {
            throw new InternalError("Something went wrong", e);
        }
    }

    private static void plantPotatoes() {
        POTATO_CLASSES.forEach(clazz ->
                Stream.of(clazz.getDeclaredFields())
                        .filter(field -> POTATOES.containsKey(field.getName()))
                        .forEach(field -> injectPotato(clazz, field))
        );
    }

    private static void injectPotato(Class<?> clazz, java.lang.reflect.Field field) {
        try {
            field.setAccessible(true);
            Object potatoInstance = POTATOES.get(clazz.getAnnotation(Potato.class).name());
            field.set(potatoInstance, POTATOES.get(field.getName()));
        } catch (IllegalAccessException e) {
            throw new InternalError("Something went wrong", e);
        }
    }
}
