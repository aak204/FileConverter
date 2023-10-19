package ru.vyatsu.service.structure;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarXML {
    @JacksonXmlProperty(isAttribute = true)
    private int id;
    @JacksonXmlProperty(isAttribute = true)
    private String type;
    private String brand;
    private String model;
    private String year;
    private EngineXML engine;
    private String color;
}
