package ru.vyatsu;

import lombok.val;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static java.lang.System.*;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainMethodTests {
    @Test
    void testMainWithCorrectArgs() {
        val outputPath = "src/test/resources/test.json";

        val outputFile = new File(outputPath);
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
        val errContent = new ByteArrayOutputStream();
        setErr(new PrintStream(errContent));

        try {
            String[] args = {"data.json"};
            Main.main(args);

            val expectedError = "Неверное количество аргументов. Для ручного режима не указывайте аргументы, для автоматического используйте 2 аргумента.";
            val actualError = errContent.toString();

            assertTrue(actualError.contains(expectedError));
        } finally {
            setErr(err);
        }
    }
}
