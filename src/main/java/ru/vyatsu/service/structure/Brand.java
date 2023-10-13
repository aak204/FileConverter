package ru.vyatsu.service.structure;
import java.util.List;

public class Brand {
    private String name;
    private List<Car> cars;

    // Геттеры и сеттеры
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Car> getCars() { return cars; }
    public void setCars(List<Car> cars) { this.cars = cars; }
}
