package ru.vyatsu.service.structure;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Представляет автомобиль с его характеристиками в формате JSON.
 */
@Getter
@Setter
@JsonPropertyOrder({"id", "type", "model", "year", "color", "engine"})
@Builder
public class CarJSON {
    private String model; // Модель автомобиля
    private String year; // Год выпуска
    private String color; // Цвет
    private EngineXML engine; // Двигатель
    private int id; // Идентификатор
    private String type; // Тип автомобиля
}

