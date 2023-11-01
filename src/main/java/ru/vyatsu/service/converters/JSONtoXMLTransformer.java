package ru.vyatsu.service.converters;

import lombok.val;
import ru.vyatsu.service.structure.Brands;
import ru.vyatsu.service.structure.CarXML;
import ru.vyatsu.service.structure.GarageXML;

/**
 * Трансформер для преобразования данных из формата JSON (в виде {@link Brands}) в XML (в виде {@link GarageXML}).
 */
public class JSONtoXMLTransformer {
    private JSONtoXMLTransformer() {
        throw new IllegalStateException("Трансформер для преобразования данных из формата JSON");
    }
    public static GarageXML transform(Brands brandsJSON) {
        val garageXML = new GarageXML();
        val carXMLList = brandsJSON.getCarBrands().stream()
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
                ).toList();

        garageXML.setCars(carXMLList);
        return garageXML;
    }
}
