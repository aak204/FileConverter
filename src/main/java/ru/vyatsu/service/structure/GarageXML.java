package ru.vyatsu.service.structure;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GarageXML {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "car")
    private List<CarXML> cars;
}