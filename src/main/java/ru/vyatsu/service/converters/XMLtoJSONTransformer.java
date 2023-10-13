package ru.vyatsu.service.converters;
import ru.vyatsu.service.structure.Brand;
import ru.vyatsu.service.structure.Car;
import ru.vyatsu.service.structure.CarXML;

import java.util.Collections;

public class XMLtoJSONTransformer {
    public Brand transform(CarXML carXML) {
        Brand brand = new Brand();
        brand.setName(carXML.getBrand());

        Car car = new Car();
        car.setModel(carXML.getModel());
        car.setYear(carXML.getYear());
        car.setColor(carXML.getColor());
        car.setEngine(carXML.getEngine());

        brand.setCars(Collections.singletonList(car));
        return brand;
    }
}
