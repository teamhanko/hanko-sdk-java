package io.hanko.sdk.json.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.hanko.sdk.json.HankoJsonParser;

import java.io.IOException;
import java.io.InputStream;

public class HankoJsonParserJackson implements HankoJsonParser {
    private final ObjectMapper mapper = new ObjectMapper();

    public HankoJsonParserJackson() {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    public <T> T parse(InputStream is, Class<T> valueType) {
        try {
            return mapper.readValue(is, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> T parse(String inputString, Class<T> valueType) {
        try {
            return mapper.readValue(inputString, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> String serialize(T value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
