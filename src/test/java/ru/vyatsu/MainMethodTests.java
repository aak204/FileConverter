package ru.vyatsu;

import lombok.val;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

class MainMethodTests {
    @Test
    void testMainWithCorrectArgs() {
        try {
            val tempOutputPath = Files.createTempFile("test", ".json");
            val args = new String[]{Objects.requireNonNull(getClass().getResourceAsStream("/data.xml")).toString(),
                tempOutputPath.toString()};
            Main.main(args);

            assertThat(Files.exists(tempOutputPath)).as("Выходной файл не был создан.").isTrue();
        } catch (Exception exception) {
            fail("Произошла ошибка при создании файла.");
        }
    }

    @Test
    void testMainWithInsufficientArgs() {
        val errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));

        try {
            val args = new String[]{"data.json"};
            Main.main(args);

            assertThat(errContent.toString()).contains("Неверное количество аргументов. " +
                "Для ручного режима не указывайте аргументы, для автоматического используйте 2 аргумента.");
        } finally {
            System.setErr(System.err);
        }
    }
}
