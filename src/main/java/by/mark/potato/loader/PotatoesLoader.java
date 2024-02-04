package by.mark.potato.loader;

import by.mark.potato.exception.PotatoException;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Objects.requireNonNull;

public class PotatoesLoader {

    public static List<? extends Class<?>> findClasses(String packageName) {
        try (Stream<Path> packagePaths = Files.walk(packageToPath(packageName))) {
            return packagePaths
                    .filter(PotatoesLoader::isJavaClass)
                    .map(path -> pathToClassName(path, packageName))
                    .map(PotatoesLoader::loadClass)
                    .toList();
        } catch (IOException | URISyntaxException e) {
            throw new PotatoException("Can not load classes", e);
        }
    }

    private static Path packageToPath(String packageName) throws URISyntaxException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL packageUrl = requireNonNull(classLoader.getResource(packageName.replace('.', '/')));

        return Paths.get(packageUrl.toURI());
    }

    private static boolean isJavaClass(Path path) {
        return Files.isRegularFile(path) && path.toString().endsWith(".class");
    }

    private static String pathToClassName(Path path, String packageName) {
        return path.toString()
                .replace(File.separator, ".")
                .replaceFirst(".+\\." + packageName, packageName)
                .replaceFirst(".class", "");
    }

    private static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (Exception e) {
            throw new PotatoException("Can not load class", e);
        }
    }
}
