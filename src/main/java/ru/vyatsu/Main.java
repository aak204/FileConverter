package ru.vyatsu;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import ru.vyatsu.service.Parser;
import ru.vyatsu.service.ParserFactory;
import ru.vyatsu.service.converters.JSONtoXMLTransformer;
import ru.vyatsu.service.converters.XMLtoJSONTransformer;
import ru.vyatsu.service.structure.Brand;
import ru.vyatsu.service.structure.BrandWrapper;
import ru.vyatsu.service.structure.Brands;
import ru.vyatsu.service.structure.GarageXML;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.out.println("Нужно ввести минимум 2 аргумента: [input file path] [name output file]");
        }
        else {
            String filePath = args[0];
            String[] fileResolutionArg1 = args[0].split("\\.");
            String[] fileResolutionArg2 = args[1].split("\\.");
            String content = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
            if(fileResolutionArg1[1].equals("xml") && fileResolutionArg2[1].equals("json")){
                try {
                    Parser xmlParser = ParserFactory.createParser("XML");
                    GarageXML garageXML = (GarageXML) xmlParser.parse(content);

                    XMLtoJSONTransformer transformer = new XMLtoJSONTransformer();
                    List<Brand> brandList = transformer.transform(garageXML);

                    // Обернуть список брендов
                    List<BrandWrapper> brandWrappers = new ArrayList<>();
                    for (Brand brand : brandList) {
                        BrandWrapper brandWrapper = new BrandWrapper();
                        brandWrapper.setBrand(brand);
                        brandWrappers.add(brandWrapper);
                    }

                    Brands brands = new Brands();
                    brands.setBrands(brandWrappers);

                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonContent = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(brands);

                    Path outputPath = Paths.get(args[1]);
                    Files.writeString(outputPath, jsonContent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if (fileResolutionArg1[1].equals("json") && fileResolutionArg2[1].equals("xml")) {
                try{
                    ObjectMapper objectMapper = new ObjectMapper();
                    Brands brands = objectMapper.readValue(content, Brands.class);
                    JSONtoXMLTransformer transformerToXML = new JSONtoXMLTransformer();
                    GarageXML garageForXML = transformerToXML.transform(brands); // brands здесь - это объект, представляющий JSON

                    XmlMapper xmlMapper = new XmlMapper();
                    xmlMapper.configure( ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true );
                    String resultXmlContent = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(garageForXML);

                    Path xmlOutputPath = Paths.get(args[1]);
                    Files.writeString(xmlOutputPath, resultXmlContent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

