package ru.vyatsu.service.converters;

import ru.vyatsu.service.structure.Brand;
import ru.vyatsu.service.structure.Brands;
import ru.vyatsu.service.structure.CarJSON;
import ru.vyatsu.service.structure.GarageXML;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Трансформер для преобразования данных из формата XML (в виде {@link GarageXML}) в JSON (в виде списка {@link Brand}).
 */
public class XMLtoJSONTransformer {
    private XMLtoJSONTransformer() {
        throw new IllegalStateException("Utility class");
    }

    public static Brands transform(GarageXML garageXML) {
        Map<String, List<CarJSON>> brandMap = new LinkedHashMap<>();

        garageXML.getCars().forEach(carXML -> {
            CarJSON car = CarJSON.builder()
                    .id(carXML.getId())
                    .type(carXML.getType())
                    .model(carXML.getModel())
                    .year(carXML.getYear())
                    .color(carXML.getColor())
                    .engine(carXML.getEngine())
                    .build();

            brandMap.computeIfAbsent(carXML.getBrand(), k -> new ArrayList<>()).add(car);
        });

        List<Brand> brandsList = brandMap.entrySet().stream()
                .map(entry -> Brand.builder()
                        .name(entry.getKey())
                        .cars(entry.getValue())
                        .build())
                .toList();

        Brands brands = new Brands();
        brands.setCarBrands(brandsList);
        return brands;
    }
}
