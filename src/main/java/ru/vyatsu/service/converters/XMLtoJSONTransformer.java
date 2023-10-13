package ru.vyatsu.service.converters;

import ru.vyatsu.service.structure.Brand;
import ru.vyatsu.service.structure.Car;
import ru.vyatsu.service.structure.CarXML;
import ru.vyatsu.service.structure.GarageXML;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLtoJSONTransformer {

    public List<Brand> transform(GarageXML garageXML) {
        Map<String, Brand> brandMap = new HashMap<>();

        for(CarXML carXML : garageXML.getCars()) {
            Brand brand = brandMap.getOrDefault(carXML.getBrand(), new Brand());
            brand.setName(carXML.getBrand());

            Car car = new Car();
            car.setModel(carXML.getModel());
            car.setYear(carXML.getYear());
            car.setColor(carXML.getColor());
            car.setEngine(carXML.getEngine());

            if(brand.getCars() == null) {
                brand.setCars(new ArrayList<>());
            }
            brand.getCars().add(car);

            brandMap.put(carXML.getBrand(), brand);
        }

        return new ArrayList<>(brandMap.values());
    }
}
