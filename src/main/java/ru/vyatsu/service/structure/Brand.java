package ru.vyatsu.service.structure;
import java.util.List;

public class Brand {
    private String name;
    private List<CarJSON> cars;

    // Геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<CarJSON> getCars() { return cars; }
    public void setCars(List<CarJSON> cars) { this.cars = cars; }
}
