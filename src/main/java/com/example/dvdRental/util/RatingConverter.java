package com.example.dvdRental.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<MpaaRating, String> {
    @Override
    public String convertToDatabaseColumn(MpaaRating rating) {

        if (rating == null) {
            return null;
        }

        return rating.getDatabaseValue();
    }

    @Override
    public MpaaRating convertToEntityAttribute(String databaseValue) {
        if (databaseValue == null) {
            return null;
        }

        return MpaaRating.fromDatabaseValue(databaseValue);
    }
}
