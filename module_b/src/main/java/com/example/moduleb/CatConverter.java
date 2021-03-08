package com.example.moduleb;

import java.util.List;
import java.util.stream.Collectors;

public class CatConverter {

    public List<Kitty> catToKitty(List<Cat> in) {
        return in.stream()
                .filter(c -> c.cuteness() > 41).filter(c -> isVeryDangerous(c))
                .filter(this::hasAKittyName)
                .map(c -> new Kitty(c.name()))
                .collect(Collectors.toList());
    }

    private boolean isVeryDangerous(Cat cat) {
        return cat.viciousness() > 10;
    }

    private boolean hasAKittyName(Cat cat) {
        // Kevins and Karls cannot be kitties
        return !cat.name().startsWith("K");
    }
}
