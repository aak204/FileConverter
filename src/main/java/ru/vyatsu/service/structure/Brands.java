package ru.vyatsu.service.structure;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * Класс контейнер для хранения списка марок автомобилей.
 */
@Getter
@Setter
@Builder
public class Brands {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    @JsonProperty("brands")
    private List<Brand> carBrands; // Список марок
}
