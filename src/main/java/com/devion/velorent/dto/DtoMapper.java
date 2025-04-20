package com.devion.velorent.dto;

import java.lang.reflect.Field;

public class DtoMapper {
    public static <S, T> T map(S source, Class<T> targetClass) {
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();

            for (Field sourceField : source.getClass().getDeclaredFields()) {
                sourceField.setAccessible(true);
                Object value = sourceField.get(source);

                try {
                    Field targetField = targetClass.getDeclaredField(sourceField.getName());
                    targetField.setAccessible(true);
                    targetField.set(target, value);
                } catch (NoSuchFieldException ignored) {
                    // Skip if target does not have the same field
                }
            }

            return target;
        } catch (Exception e) {
            throw new RuntimeException("Failed to map DTO", e);
        }
    }
}

