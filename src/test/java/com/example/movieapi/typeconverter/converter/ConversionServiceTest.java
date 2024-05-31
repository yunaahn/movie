package com.example.movieapi.typeconverter.converter;

import com.example.movieapi.converter.IntegerToStringConverter;
import com.example.movieapi.converter.StringToIntegerConverter;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;

public class ConversionServiceTest {

    @Test
    void convertTest() {

        DefaultConversionService conversionService = new DefaultConversionService();
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new IntegerToStringConverter());
    }
}
