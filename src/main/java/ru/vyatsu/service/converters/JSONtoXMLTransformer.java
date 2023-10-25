package ru.vyatsu.service.converters;

import ru.vyatsu.service.Transformer;
import ru.vyatsu.service.structure.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Трансформер для преобразования данных из формата JSON (в виде {@link Brands}) в XML (в виде {@link GarageXML}).
 */
public class JSONtoXMLTransformer implements Transformer<Brands, GarageXML> {
    @Override
    public GarageXML transform(Brands brandsJSON) {
        GarageXML garageXML = new GarageXML();
        List<CarXML> carXMLList = new ArrayList<>();

        for (Brand brand : brandsJSON.getBrands()) {
            for (CarJSON car : brand.getCars()) {
                CarXML carXML = new CarXML();
                carXML.setBrand(brand.getName());
                carXML.setModel(car.getModel());
                carXML.setYear(car.getYear());
                carXML.setColor(car.getColor());
                carXML.setEngine(car.getEngine());
                carXML.setId(car.getId());
                carXML.setType(car.getType());

                carXMLList.add(carXML);
            }
        }

        garageXML.setCars(carXMLList);
        return garageXML;
    }
}
