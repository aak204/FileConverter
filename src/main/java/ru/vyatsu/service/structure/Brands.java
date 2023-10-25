package ru.vyatsu.service.structure;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * Класс контейнер для хранения списка марок автомобилей.
 */
@Getter
@Setter
public class Brands {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    private List<Brand> brands; // Список марок
}
