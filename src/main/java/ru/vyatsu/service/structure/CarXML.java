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
    private int id; // Идентификатор
    @JacksonXmlProperty(isAttribute = true)
    private String type; // Тип автомобиля
    private String brand; // Марка
    private String model; // Модель
    private String year; // Год выпуска
    private EngineXML engine; // Двигатель
    private String color; // Цвет
}
