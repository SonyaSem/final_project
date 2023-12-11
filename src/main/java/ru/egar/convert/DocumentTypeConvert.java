package ru.egar.convert;

import ru.egar.enums.DocumentType;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class DocumentTypeConvert implements AttributeConverter<DocumentType, String> {

    @Override
    public String convertToDatabaseColumn(DocumentType documentType) {
        if (documentType == null) {
            return null;
        }
        return documentType.getType();
    }

    @Override
    public DocumentType convertToEntityAttribute(String type) {
        if (type == null) {
            return null;
        }

        return Stream.of(DocumentType.values())
                .filter(c -> c.getType().equals(type))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}

