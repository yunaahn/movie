package com.example.movieapi.converter;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

@Slf4j
public class StringToIntegerConverter implements Converter<String, Integer> {
    @Override
    public Integer convert(String source) {

        log.info("convert string to Integer = " + source);

        return Integer.valueOf(source);
    }
}
