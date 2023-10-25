package ru.vyatsu.service.structure;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Представляет марку автомобиля со списком его моделей.
 */
@Getter
@Setter
@JsonTypeName("brand")
public class Brand {
    private String name; // Название марки
    private List<CarJSON> cars; // Список моделей автомобилей этой марки
}

