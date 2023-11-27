package ru.vyatsu.service.factory;

public interface TransformerFactory<I, O> {
    O transform(final I input);
}