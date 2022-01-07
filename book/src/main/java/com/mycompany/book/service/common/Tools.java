package com.mycompany.book.service.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;

public class Tools {
    public static <TEntity> Class<TEntity> getTypeArgument(Object instance){
        return (Class<TEntity>) ((ParameterizedType) instance.getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    public static <TEntity> Class<TEntity[]> getTypeArgumentOfArray(Object instance) {
        Class<TEntity> entityType = getTypeArgument(instance);
        TEntity[] array =  (TEntity[]) Array.newInstance(entityType, 0);
        return (Class<TEntity[]>) array.getClass();
    }

    public static <TEntity> TEntity[] newArrayInstance(Class<TEntity> entityType, int length){
        return (TEntity[]) Array.newInstance(entityType, length);
    }

    public static <TEntity> TEntity[] cloneDeep(TEntity[] results) {
        TEntity[] copy = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String object_str = mapper.writeValueAsString(results);
            try {
                copy = (TEntity[]) mapper.readValue(object_str, results.getClass());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return copy;
    }
}
