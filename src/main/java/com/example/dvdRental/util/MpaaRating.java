package com.example.dvdRental.util;

import lombok.Getter;

import java.util.stream.Stream;


@Getter
public enum MpaaRating {
    G("G"),
    PG("PG"),
    PG_13("PG-13"),
    R("R"),
    NC_17("NC-17");


    private final String databaseValue;

    MpaaRating(String databaseValue) {
        this.databaseValue = databaseValue;
    }

    public static MpaaRating fromDatabaseValue(String databaseValue) {
        return Stream.of(MpaaRating.values())
                .filter(mpaaRating -> mpaaRating.getDatabaseValue().equals(databaseValue))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}