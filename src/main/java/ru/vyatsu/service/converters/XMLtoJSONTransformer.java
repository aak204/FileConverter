package ru.vyatsu.service.converters;

import lombok.experimental.UtilityClass;
import lombok.val;
import ru.vyatsu.service.structure.Brand;
import ru.vyatsu.service.structure.Brands;
import ru.vyatsu.service.structure.CarJSON;
import ru.vyatsu.service.structure.GarageXML;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Трансформер для преобразования данных из формата XML (в виде {@link GarageXML}) в JSON (в виде списка {@link Brand}).
 */
@UtilityClass
public class XMLtoJSONTransformer {
    public Brands transform(final GarageXML garageXML) {
        val brandMap = new LinkedHashMap<String, List<CarJSON>>();

        garageXML.getCars().forEach(carXML -> {
            val car = CarJSON.builder()
                    .id(carXML.getId())
                    .type(carXML.getType())
                    .model(carXML.getModel())
                    .year(carXML.getYear())
                    .color(carXML.getColor())
                    .engine(carXML.getEngine())
                    .build();

            brandMap.computeIfAbsent(carXML.getBrand(), brand -> new ArrayList<>()).add(car);
        });

        return Brands.builder()
                .carBrands(brandMap.entrySet().stream()
                        .map(entry -> Brand.builder()
                                .name(entry.getKey())
                                .cars(entry.getValue())
                                .build())
                        .toList())
                .build();
    }
}
