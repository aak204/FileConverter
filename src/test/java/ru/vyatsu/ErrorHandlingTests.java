package ru.vyatsu;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.vyatsu.service.ConversionException;
import ru.vyatsu.service.ConversionService;
import ru.vyatsu.service.ConversionType;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

import static java.nio.file.Files.exists;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static ru.vyatsu.service.ConversionType.*;

class ErrorHandlingTests {

    private String getPathFromResource(String resourcePath) throws URISyntaxException {
        return Paths.get(Objects.requireNonNull(getClass().getResource(resourcePath)).toURI()).toString();
    }

    @Test
    void testConversionWithInvalidFormat() throws URISyntaxException {
        assertConversionException(getPathFromResource("/test.invalid"), XML_TO_JSON);
    }

    @Test
    void testConversionWithNonExistentInputFile() throws URISyntaxException {
        assertConversionException(getPathFromResource("/non_existent.xml"), XML_TO_JSON);
    }

    @Test
    void testConversionWithIncorrectConversionType() throws URISyntaxException {
        assertConversionException(getPathFromResource("/data.xml"), JSON_TO_XML);
    }

    @Test
    void testConversionWithUnsupportedFileFormat() throws URISyntaxException {
        assertConversionException(getPathFromResource("/data.txt"), JSON_TO_XML);
    }

    @Test
    void testConversionWithEmptyInputFile() throws URISyntaxException {
        assertConversionException(getPathFromResource("/empty.xml"), XML_TO_JSON);
    }

    void assertConversionException(final String inputPath, final ConversionType conversionType) {
        val outputPath = Objects.requireNonNull(getClass().getResourceAsStream("/data.json")).toString();

        assertThatThrownBy(() ->
            ConversionService.getInstance().convert(inputPath, outputPath, conversionType))
            .isInstanceOf(ConversionException.class);

        assertThat(exists(Paths.get(outputPath)))
            .as("После неудачного преобразования выходной файл не должен существовать.")
            .isFalse();
    }
}
