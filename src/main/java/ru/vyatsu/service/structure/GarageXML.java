package ru.vyatsu.service.structure;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class GarageXML {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "car")
    private List<CarXML> cars;

    // Геттеры и сеттеры
    public List<CarXML> getCars() { return cars; }
    public void setCars(List<CarXML> cars) { this.cars = cars; }
}