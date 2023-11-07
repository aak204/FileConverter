package ru.vyatsu;

import org.junit.jupiter.api.Test;
import ru.vyatsu.service.ConversionService;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

class ConversionTests {
    @Test
    void testConvertXMLtoJSONSuccess() throws Exception {
        assertEquals(
                Files.readString(Paths.get("src/test/resources/data.json")),
                ConversionService.convertXMLtoJSON(
                        Files.readString(Paths.get("src/test/resources/data.xml"))
                )
        );
    }

    @Test
    void testConvertJSONtoXMLSuccess() throws Exception {
        assertEquals(
                Files.readString(Paths.get("src/test/resources/data.xml")),
                ConversionService.convertJSONtoXML(
                        Files.readString(Paths.get("src/test/resources/data.json"))
                )
        );
    }
}
