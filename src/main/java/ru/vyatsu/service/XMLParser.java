package ru.vyatsu.service;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import ru.vyatsu.service.structure.GarageXML;

public class XMLParser implements Parser {
    @Override
    public Object parse(String content) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue(content, GarageXML.class);
    }
}

