package ru.vyatsu;

import org.junit.jupiter.api.Test;
import ru.vyatsu.service.ConversionService;

import static java.nio.file.Files.readString;
import static java.nio.file.Paths.get;
import static org.junit.Assert.assertEquals;

class ConversionTests {
    @Test
    void testConvertXMLtoJSONSuccess() throws Exception {
        assertEquals(
                readString(get("src/test/resources/data.json")),
                ConversionService.convertXMLtoJSON(
                        readString(get("src/test/resources/data.xml"))
                )
        );
    }

    @Test
    void testConvertJSONtoXMLSuccess() throws Exception {
        assertEquals(
                readString(get("src/test/resources/data.xml")),
                ConversionService.convertJSONtoXML(
                        readString(get("src/test/resources/data.json"))
                )
        );
    }
}
