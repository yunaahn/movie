package com.example.movieapi.typeconverter.converter;

import com.example.movieapi.converter.IntegerToStringConverter;
import com.example.movieapi.converter.StringToIntegerConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConverterTest {

    @Test
    void convert() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer integer = converter.convert("123");
        Assertions.assertEquals(Integer.valueOf(123), integer);

    }

    @Test
    void convert2() {
        IntegerToStringConverter converter = new IntegerToStringConverter();
        String string = converter.convert(123);
        Assertions.assertEquals(String.valueOf("123"), String.valueOf(string));
    }
}
