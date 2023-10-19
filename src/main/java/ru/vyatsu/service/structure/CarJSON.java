package ru.vyatsu.service.structure;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonPropertyOrder({"id", "type", "model", "year", "color", "engine"})
public class CarJSON {
    private String model;
    private String year;
    private String color;
    private EngineXML engine;
    private int id;
    private String type;
}
