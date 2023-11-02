package ru.vyatsu;

import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vyatsu.service.ConversionService;
import java.nio.file.Files;
import java.nio.file.Paths;

class ConversionTests {
    @Test
    void testConvertXMLtoJSONSuccess() throws Exception {
        val inputXml = new String(Files.readAllBytes(Paths.get("src/test/resources/data.xml")));

        val actualXml = ConversionService.convertXMLtoJSON(inputXml);

        val expectedJson = new String(Files.readAllBytes(Paths.get("src/test/resources/data.json")));

        Assertions.assertEquals(expectedJson, actualXml, "Преобразованный JSON не соответствует ожидаемому XML.");
    }

    @Test
    void testConvertJSONtoXMLSuccess() throws Exception {
        val inputJson = new String(Files.readAllBytes(Paths.get("src/test/resources/data.json")));

        val actualXml = ConversionService.convertJSONtoXML(inputJson);

        val expectedXml = new String(Files.readAllBytes(Paths.get("src/test/resources/data.xml")));

        Assertions.assertEquals(expectedXml, actualXml, "Преобразованный XML не соответствует ожидаемому JSON.");
    }
}
