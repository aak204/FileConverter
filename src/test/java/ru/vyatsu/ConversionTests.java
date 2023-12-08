package ru.vyatsu;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.FieldDefaults;
import lombok.val;
import org.junit.jupiter.api.Test;
import ru.vyatsu.service.converters.JSONtoXMLConverter;
import ru.vyatsu.service.converters.XMLtoJSONConverter;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import java.io.ByteArrayOutputStream;

@FieldDefaults(makeFinal = true)
class ConversionTests {
    private ObjectMapper objectMapper = new ObjectMapper();
    private XmlMapper xmlMapper = new XmlMapper();
    @Test
    void testConvertXMLtoJSON() throws Exception {
        val converter = new XMLtoJSONConverter();

        try (val inputStream = getClass().getResourceAsStream("/data.xml");
             val outputStream = new ByteArrayOutputStream()) {

            converter.convert(inputStream, outputStream);

            assertThat(objectMapper.readTree(outputStream.toString(UTF_8)))
                .isEqualTo(objectMapper.readTree(getClass().getResourceAsStream("/data.json")));
        }
    }

    @Test
    void testConvertJSONtoXML() throws Exception {
        val converter = new JSONtoXMLConverter();

        try (val inputStream = getClass().getResourceAsStream("/data.json");
             val outputStream = new ByteArrayOutputStream()) {

            converter.convert(inputStream, outputStream);

            assertThat(xmlMapper.readTree(outputStream.toString(UTF_8)))
                .isEqualTo(xmlMapper.readTree(getClass().getResourceAsStream("/data.xml")));
        }
    }
}
