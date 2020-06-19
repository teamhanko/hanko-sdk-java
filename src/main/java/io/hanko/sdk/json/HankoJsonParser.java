package io.hanko.sdk.json;

import java.io.InputStream;

public interface HankoJsonParser {
    <T> T parse(InputStream is, Class<T> valueType);
    <T> T parse(String inputString, Class<T> valueType);
    <T> String serialize(T value);
}
