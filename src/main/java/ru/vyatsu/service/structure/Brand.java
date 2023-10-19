package ru.vyatsu.service.structure;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Brand {
    private String name;
    private List<CarJSON> cars;
}
