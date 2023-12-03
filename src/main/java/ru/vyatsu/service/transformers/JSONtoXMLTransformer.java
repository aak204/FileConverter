package ru.vyatsu.service.transformers;

import lombok.experimental.UtilityClass;
import ru.vyatsu.service.structure.Brands;
import ru.vyatsu.service.structure.CarXML;
import ru.vyatsu.service.structure.GarageXML;

/**
 * Трансформер для преобразования данных из формата JSON (в виде {@link Brands}) в XML (в виде {@link GarageXML}).
 */
@UtilityClass
public class JSONtoXMLTransformer {
    public GarageXML transform(final Brands brandsJSON) {
        return GarageXML.builder()
            .cars(brandsJSON.getCarBrands().stream()
                .flatMap(brand -> brand.getCars().stream()
                        .map(car -> CarXML.builder()
                                .brand(brand.getName())
                                .model(car.getModel())
                                .year(car.getYear())
                                .color(car.getColor())
                                .engine(car.getEngine())
                                .id(car.getId())
                                .type(car.getType())
                            .build()))
                .toList())
            .build();
    }
}
