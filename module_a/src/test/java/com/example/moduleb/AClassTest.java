package com.example.moduleb;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AClassTest {

    @Test
    public void poorTest() {
        AClass underTest = new AClass();
        Optional<Widget> actual = underTest.find(Collections.emptyList());
        assertNotNull(actual);
    }

}