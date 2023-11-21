package ru.vyatsu.service.structure;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

import java.util.List;

/**
 * Представляет марку автомобиля со списком его моделей.
 */
@Getter
@Setter
@JsonTypeName("brand")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Brand {
    String name; // Название марки
    List<CarJSON> cars; // Список моделей автомобилей этой марки
}
