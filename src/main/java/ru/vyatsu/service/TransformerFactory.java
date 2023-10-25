package ru.vyatsu.service;

import ru.vyatsu.service.converters.JSONtoXMLTransformer;
import ru.vyatsu.service.converters.XMLtoJSONTransformer;

/**
 * Фабрика для создания экземпляров {@link Transformer}.
 */
public class TransformerFactory {

    /**
     * Создаёт трансформер в зависимости от указанного типа.
     *
     * @param type Тип трансформера, который необходимо создать.
     * @return Экземпляр трансформера.
     * @throws IllegalArgumentException Если указанный тип трансформера неизвестен.
     */
    public static Transformer<?, ?> createTransformer(String type) {
        return switch (type.toUpperCase()) {
            case "XMLTOJSON" -> new XMLtoJSONTransformer();
            case "JSONTOXML" -> new JSONtoXMLTransformer();
            default -> throw new IllegalArgumentException("Неизвестный тип");
        };
    }
}
