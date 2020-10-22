package by.mark.potato.context;

import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class PotatoUtils {

    @SuppressWarnings("unchecked")
    public <T> T getPotato(String name) {
        return (T) Objects.requireNonNull(PotatoApplication.POTATOES.get(name), "Potato can not be found");
    }

    @SuppressWarnings("unchecked")
    public <T> T findPotato(String name) {
        return (T) PotatoApplication.POTATOES.get(name);
    }
}
