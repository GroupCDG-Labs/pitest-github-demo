package com.example.moduleb;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class CatConverterTest {

    @Test
    void whenThereAreNoCatsThereCanBeNoKitties() {
        CatConverter underTest = new CatConverter();
        assertThat(underTest.catToKitty(Collections.emptyList())).isEmpty();
    }
}