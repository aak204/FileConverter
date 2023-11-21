package ru.vyatsu.service.transformers;

import lombok.experimental.UtilityClass;
import ru.vyatsu.service.structure.Brand;
import ru.vyatsu.service.structure.Brands;
import ru.vyatsu.service.structure.CarJSON;
import ru.vyatsu.service.structure.GarageXML;
import ru.vyatsu.service.structure.CarXML;
import java.util.stream.Collectors;

/**
 * Трансформер для преобразования данных из формата XML (в виде {@link GarageXML}) в JSON (в виде списка {@link Brand}).
 */
@UtilityClass
public class XMLtoJSONTransformer {
    public Brands transform(final GarageXML garageXML) {
        return Brands.builder()
                .carBrands(garageXML.getCars().stream()
                        .collect(Collectors.groupingBy(CarXML::getBrand))
                        .entrySet().stream()
                        .map(entry -> Brand.builder()
                                .name(entry.getKey())
                                .cars(entry.getValue().stream()
                                        .map(carXML -> CarJSON.builder()
                                                .id(carXML.getId())
                                                .type(carXML.getType())
                                                .model(carXML.getModel())
                                                .year(carXML.getYear())
                                                .color(carXML.getColor())
                                                .engine(carXML.getEngine())
                                                .build())
                                        .toList())
                                .build())
                        .toList())
                .build();
    }
}
