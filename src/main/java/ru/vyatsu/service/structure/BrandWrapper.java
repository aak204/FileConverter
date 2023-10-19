package ru.vyatsu.service.structure;

import lombok.Getter;

@Getter
public class BrandWrapper {
    private Brand brand;

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
