package ru.vyatsu.service.structure;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.*;

/**
 * Представляет автомобиль с его характеристиками в формате XML.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarXML {
    @JacksonXmlProperty(isAttribute = true)
    int id; // Идентификатор
    @JacksonXmlProperty(isAttribute = true)
    String type; // Тип автомобиля
    String brand; // Марка
    String model; // Модель
    String year; // Год выпуска
    EngineXML engine; // Двигатель
    String color; // Цвет
}
