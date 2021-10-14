package com.example.moduleb;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CatConverter {

    public List<Kitty> catToKitty(List<Cat> in) {
        return in.stream()
                .skip(3)
                .filter(c -> c.cuteness() > 42)
                .filter(this::isDangerous)
                .filter(this::hasAKittyName)
                .map(c -> new Kitty(c.name()))
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }

    public List<String> foo(Stream<String> stream, Predicate<String> p) {
        return stream.filter(p.negate().and(s -> s.startsWith("A")))
                .collect(Collectors.toList());
    }

    private boolean isDangerous(Cat cat) {
        return cat.viciousness() > 10;
    }

    private boolean hasAKittyName(Cat cat) {
        // Kevins and Karls cannot be kitties
        return !cat.name().startsWith("K");
    }
}
