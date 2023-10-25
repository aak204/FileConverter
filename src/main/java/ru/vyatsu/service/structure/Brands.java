package ru.vyatsu.service.structure;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * Класс контейнер для хранения списка марок автомобилей.
 */
@Getter
@Setter
public class Brands {
    private List<Brand> brands; // Список марок
}
