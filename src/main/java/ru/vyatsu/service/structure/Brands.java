package ru.vyatsu.service.structure;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.WRAPPER_OBJECT;


/**
 * Класс контейнер для хранения списка марок автомобилей.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brands {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = WRAPPER_OBJECT)
    @JsonProperty("brands")
    private List<Brand> carBrands; // Список марок
}
