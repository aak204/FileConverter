package ru.vyatsu.service.structure;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class CarXML {
    @JacksonXmlProperty(isAttribute = true)
    private int id;

    @JacksonXmlProperty(isAttribute = true)
    private String type;

    private String brand;
    private String model;
    private String year;
    private Engine engine;
    private String color;

    // Геттеры и сеттеры
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
    public Engine getEngine() { return engine; }
    public void setEngine(Engine engine) { this.engine = engine; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
}
