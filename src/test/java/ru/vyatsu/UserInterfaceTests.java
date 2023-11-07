package ru.vyatsu;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vyatsu.service.MenuService;

import java.io.ByteArrayInputStream;

import static org.junit.Assert.assertEquals;

class UserInterfaceTests {
    @Test
    void testGetUserChoiceValidInput() {
        val input = "1" + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        try {
            assertEquals(1, MenuService.getUserChoice());
        } finally {
            System.setIn(System.in); // Возвращаем стандартный ввод
        }
    }
}