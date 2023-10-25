package ru.vyatsu.service;

public interface Transformer<In, Out> {
    Out transform(In input);
}
