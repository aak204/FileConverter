package ru.vyatsu.service.converters;

import ru.vyatsu.service.Transformer;
import ru.vyatsu.service.structure.Brand;
import ru.vyatsu.service.structure.CarJSON;
import ru.vyatsu.service.structure.CarXML;
import ru.vyatsu.service.structure.GarageXML;
import java.util.*;

public class XMLtoJSONTransformer implements Transformer<GarageXML, List<Brand>> {
    @Override
    public List<Brand> transform(GarageXML garageXML) {
        Map<String, Brand> brandMap = new LinkedHashMap<>();

        for(CarXML carXML : garageXML.getCars()) {
            Brand brand = brandMap.getOrDefault(carXML.getBrand(), new Brand());
            brand.setName(carXML.getBrand());

            CarJSON car = new CarJSON();
            car.setId(carXML.getId());
            car.setType(carXML.getType());
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
