package ru.vyatsu;

import lombok.val;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainMethodTests {
    @Test
    void testMainWithInsufficientArgs() {
        val originalErr = System.err;

        try {
            val errContent = new ByteArrayOutputStream();
            val printStream = new PrintStream(errContent);
            System.setErr(printStream);

            val args = new String[]{"data.json"};
            Main.main(args);

            val expectedError = "Неверное количество аргументов. Для ручного режима не указывайте аргументы, для автоматического используйте 2 аргумента.";
            val actualError = errContent.toString();
            assertTrue(actualError.contains(expectedError));

        } finally {
            System.setErr(originalErr);
        }
    }
}
