package ru.vyatsu.service;

/**
 * Обобщённый интерфейс для трансформации данных из одного типа в другой.
 *
 * @param <In> Тип входных данных.
 * @param <Out> Тип выходных данных.
 */
public interface Transformer<In, Out> {
    Out transform(In input);
}
