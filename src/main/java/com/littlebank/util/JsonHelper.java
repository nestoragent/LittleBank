package com.littlebank.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

public class JsonHelper {

    public static String convertAnObjectListToJsonArray(List<?> list) {
        final ObjectMapper mapper = new ObjectMapper();
        final StringWriter sw = new StringWriter();
        try {
            mapper.writeValue(sw, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sw.toString();
    }
}
