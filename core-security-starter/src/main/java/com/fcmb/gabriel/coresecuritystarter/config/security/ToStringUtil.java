package com.fcmb.gabriel.coresecuritystarter.config.security;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ToStringUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
            .registerModule(new JavaTimeModule());

    public static String print(Object obj) {
        try {
            return (OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
        } catch (Exception e) {
            return "";
        }
    }
}
