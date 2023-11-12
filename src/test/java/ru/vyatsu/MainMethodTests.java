package ru.vyatsu;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainMethodTests {
    @Test
    void testMainWithCorrectArgs() {
        final String outputPath = "src/test/resources/test.json";

        File outputFile = new File(outputPath);
        if (outputFile.exists() && !outputFile.delete()) {
            fail("Не удалось удалить существующий выходной файл перед тестированием.");
        }

        try {
            String[] args = {"src/test/resources/data.xml", outputPath};
            Main.main(args);

            assertTrue(outputFile.exists(), "Выходной файл не был создан.");
        } finally {
            if (outputFile.exists() && !outputFile.delete()) {
                fail("Не удалось удалить выходной файл после тестирования.");
            }
        }
    }

    @Test
    void testMainWithInsufficientArgs() {
        final PrintStream originalErr = System.err;
        final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        try {
            String[] args = {"data.json"};
            Main.main(args);

            final String expectedError = "Неверное количество аргументов. Для ручного режима не указывайте аргументы, для автоматического используйте 2 аргумента.";
            final String actualError = errContent.toString();

            assertTrue(actualError.contains(expectedError));
        } finally {
            System.setErr(originalErr);
        }
    }
}
