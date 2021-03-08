package com.example.moduleb;

import java.util.List;
import java.util.Optional;

public class AClass {

    public Optional<Widget> find(List<String> in) {
        return in.stream()
                .filter(s -> s.startsWith("Z")).filter(s -> s.startsWith("Q"))
                .map(Widget::new)
                .findAny();
    }

}
