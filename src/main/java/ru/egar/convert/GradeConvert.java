package ru.egar.convert;

import ru.egar.enums.Grade;

import javax.persistence.AttributeConverter;

import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class GradeConvert implements AttributeConverter<Grade, String> {

    @Override
    public String convertToDatabaseColumn(Grade grade) {
        if (grade == null) {
            return null;
        }
        return grade.getResult();
    }

    @Override
    public Grade convertToEntityAttribute(String result) {
        if (result == null) {
            return null;
        }

        return Stream.of(Grade.values())
                .filter(c -> c.getResult().equals(result))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
