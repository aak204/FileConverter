package ru.vyatsu.service.structure;

public class CarXML {
    private String brand;
    private String model;
    private String year;
    private Engine engine;
    private String color;

    // Геттеры и сеттеры
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
