package ru.vyatsu.service.structure;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Представляет марку автомобиля со списком его моделей.
 */
@Getter
@Setter
public class Brand {
    private String name; // Название марки
    private List<CarJSON> cars; // Список моделей автомобилей этой марки
}

