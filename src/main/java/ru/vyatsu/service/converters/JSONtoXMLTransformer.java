package ru.vyatsu.service.converters;

import ru.vyatsu.service.TransformerType;
import ru.vyatsu.service.structure.Brands;
import ru.vyatsu.service.structure.CarXML;
import ru.vyatsu.service.structure.GarageXML;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Трансформер для преобразования данных из формата JSON (в виде {@link Brands}) в XML (в виде {@link GarageXML}).
 */
public class JSONtoXMLTransformer implements TransformerType<Brands, GarageXML> {
    @Override
    public GarageXML transform(Brands brandsJSON) {
        var garageXML = new GarageXML();
        List<CarXML> carXMLList = brandsJSON.getCarBrands().stream()
                .flatMap(brand -> brand.getCars().stream()
                        .map(car -> CarXML.builder()
                                .brand(brand.getName())
                                .model(car.getModel())
                                .year(car.getYear())
                                .color(car.getColor())
                                .engine(car.getEngine())
                                .id(car.getId())
                                .type(car.getType())
                                .build()
                        )
                ).collect(Collectors.toList());

        garageXML.setCars(carXMLList);
        return garageXML;
    }
}
