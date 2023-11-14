package ru.vyatsu;

import org.junit.jupiter.api.Test;
import ru.vyatsu.service.ConversionService;

import java.nio.file.Paths;

import static java.nio.file.Files.readString;
import static org.junit.Assert.assertEquals;

class ConversionTests {
    @Test
    void testConvertXMLtoJSONSuccess() throws Exception {
        assertEquals(
                readString(Paths.get("src/test/resources/data.json")),
                ConversionService.convertXMLtoJSON(
                        readString(Paths.get("src/test/resources/data.xml"))
                )
        );
    }

    @Test
    void testConvertJSONtoXMLSuccess() throws Exception {
        assertEquals(
                readString(Paths.get("src/test/resources/data.xml")),
                ConversionService.convertJSONtoXML(
                        readString(Paths.get("src/test/resources/data.json"))
                )
        );
    }
}
