package ru.vyatsu.service.structure;

public class CarJSON {
    private String model;
    private String year;
    private String color;
    private EngineXML engine;

    // Геттеры и сеттеры
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public EngineXML getEngine() { return engine; }
    public void setEngine(EngineXML engine) { this.engine = engine; }
}

