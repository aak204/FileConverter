package ru.vyatsu.service.structure;

import lombok.Getter;

import java.util.List;

@Getter
public class Brands {
    private List<BrandWrapper> brands;

    public void setBrands(List<BrandWrapper> brands) {
        this.brands = brands;
    }
}

