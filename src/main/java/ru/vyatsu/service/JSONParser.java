package ru.vyatsu.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.vyatsu.service.structure.Brand;

public class JSONParser implements Parser {
    @Override
    public Object parse(String content) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(content, Brand.class);
    }
}
