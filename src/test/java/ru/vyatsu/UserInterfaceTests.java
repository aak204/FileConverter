package ru.vyatsu;

import lombok.val;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.vyatsu.service.MenuUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import static java.lang.System.*;
import static org.assertj.core.api.Assertions.assertThat;

class UserInterfaceTests {
    private ByteArrayOutputStream testOut;
    private PrintStream printStreamOut;
    private ByteArrayInputStream testIn;

    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        printStreamOut = new PrintStream(testOut);
        setOut(printStreamOut);
    }

    private void setUpInput(final String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        MenuUtils.setScanner(new Scanner(testIn));
    }

    @AfterEach
    public void restoreSystemInputOutput() throws IOException {
        if (testIn != null) {
            testIn.close();
        }
        MenuUtils.setScanner(new Scanner(in));

        if (printStreamOut != null) {
            printStreamOut.close();
        }
        setOut(System.out);
    }

    @Test
    void testGetUserChoiceValidInput() {
        setUpInput("1" + lineSeparator());
        assertThat(MenuUtils.getUserChoice()).isEqualTo(1);
    }

    @Test
    void testGetUserChoiceWithInvalidInput() {
        val input = "4" + lineSeparator() + "1" + lineSeparator();
        setUpInput(input);
        MenuUtils.getUserChoice();
        val expectedOutput = "Выберите операцию:" + lineSeparator() +
            "1. XML в JSON" + lineSeparator() +
            "2. JSON в XML" + lineSeparator() +
            "Неверный выбор: 4. Пожалуйста, выберите 1 или 2." + lineSeparator() +
            "Выберите операцию:" + lineSeparator() +
            "1. XML в JSON" + lineSeparator() +
            "2. JSON в XML" + lineSeparator();
        assertThat(testOut.toString()).startsWith(expectedOutput);
    }
}
