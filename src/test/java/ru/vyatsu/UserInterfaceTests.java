package ru.vyatsu;

import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.vyatsu.service.MenuService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static java.lang.System.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class UserInterfaceTests {
    private final InputStream systemIn = in;
    private final PrintStream systemOut = out;
    private ByteArrayOutputStream testOut;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        setOut(new PrintStream(testOut));
    }

    private void setUpInput(String data) {
        val testIn = new ByteArrayInputStream(data.getBytes());
        setIn(testIn);
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        setIn(systemIn);
        setOut(systemOut);
    }

    @Test
    void testGetUserChoiceValidInput() {
        setUpInput("1" + lineSeparator());
        assertEquals(1, MenuService.getUserChoice());
    }

    @Test
    void testGetUserChoiceWithInvalidInput() {
        val input = "4" + lineSeparator() + "1" + lineSeparator();
        setUpInput(input);
        MenuService.getUserChoice();
        val expectedOutput = "Выберите операцию:" + lineSeparator() +
                "1. XML в JSON" + lineSeparator() +
                "2. JSON в XML" + lineSeparator() +
                "Неверный выбор: 4. Пожалуйста, выберите 1 или 2" + lineSeparator() +
                "Выберите операцию:" + lineSeparator() +
                "1. XML в JSON" + lineSeparator() +
                "2. JSON в XML" + lineSeparator();
        assertTrue(testOut.toString().startsWith(expectedOutput));
    }
}