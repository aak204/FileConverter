package ru.vyatsu;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.vyatsu.service.Parser;
import ru.vyatsu.service.ParserFactory;
import ru.vyatsu.service.converters.XMLtoJSONTransformer;
import ru.vyatsu.service.structure.Brand;
import ru.vyatsu.service.structure.GarageXML;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String filePath = "src\\main\\resources\\data.xml";
        String xmlContent = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
        try {
            Parser xmlParser = ParserFactory.createParser("XML");
            GarageXML garageXML = (GarageXML) xmlParser.parse(xmlContent);

            XMLtoJSONTransformer transformer = new XMLtoJSONTransformer();
            List<Brand> brands = transformer.transform(garageXML);

            ObjectMapper objectMapper = new ObjectMapper();
            String jsonContent = objectMapper.writeValueAsString(brands);

            Path outputPath = Paths.get("new_data.json");
            Files.writeString(outputPath, jsonContent);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
