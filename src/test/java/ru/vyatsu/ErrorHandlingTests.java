package ru.vyatsu;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.vyatsu.service.ConversionException;
import ru.vyatsu.service.ConversionService;
import ru.vyatsu.service.ConversionType;

import java.nio.file.Paths;

import static java.nio.file.Files.exists;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static ru.vyatsu.service.ConversionType.*;

class ErrorHandlingTests {

    @Test
    void testConversionWithInvalidFormat() {
        assertConversionException("src/test/resources/test.invalid", XML_TO_JSON);
    }

    @Test
    void testConversionWithNonExistentInputFile() {
        assertConversionException("src/test/resources/non_existent.xml", XML_TO_JSON);
    }

    @Test
    void testConversionWithIncorrectConversionType() {
        assertConversionException("src/test/resources/data.xml", JSON_TO_XML);
    }

    @Test
    void testConversionWithUnsupportedFileFormat() {
        assertConversionException("src/test/resources/data.txt", JSON_TO_XML);
    }

    @Test
    void testConversionWithEmptyInputFile() {
        assertConversionException("src/test/resources/empty.xml", XML_TO_JSON);
    }

    void assertConversionException(final String inputPath, final ConversionType conversionType) {
        val outputPath = "src/test/resources/output.json";

        assertThatThrownBy(() ->
                ConversionService.getInstance().convert(inputPath, outputPath, conversionType))
                .isInstanceOf(ConversionException.class);

        assertThat(exists(Paths.get(outputPath)))
                .as("После неудачного преобразования выходной файл не должен существовать.")
                .isFalse();
    }
}
