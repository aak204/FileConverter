package ru.vyatsu.service;

/**
 * Фабрика для создания экземпляров {@link TransformerType}.
 */

public class TransformerFactory {

    private TransformerFactory() {
        throw new UnsupportedOperationException("Этот класс является утилитарным и не может быть инсталлирован");
    }

    public static Object createTransformer(String type, Object input) {
        try {
            return TransformerType.valueOf(type.toUpperCase()).transform(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неизвестный тип", e);
        }
    }
}