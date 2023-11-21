package ru.vyatsu.service.structure;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

/**
 * Представляет автомобиль с его характеристиками в формате JSON.
 */
@Getter
@Setter
@JsonPropertyOrder({"id", "type", "model", "year", "color", "engine"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarJSON {
    String model; // Модель автомобиля
    String year; // Год выпуска
    String color; // Цвет
    EngineXML engine; // Двигатель
    int id; // Идентификатор
    String type; // Тип автомобиля
}
