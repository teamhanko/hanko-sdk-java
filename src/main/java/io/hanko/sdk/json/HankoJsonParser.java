package io.hanko.sdk.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.hanko.sdk.exception.HankoClientException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Custom Json parser used for serializing Hanko API payloads and deserializing Hanko API responses.
 */
public class HankoJsonParser {
    private final String COULD_NOT_DESERIALIZE_FORMAT = "Could not deserialize: %s";

    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Construct a HankoJsonParser.
     */
    public HankoJsonParser() {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * Deserialize InputStream {@code is} into object of type {@code T}.
     * @param is the InputStream
     * @param valueType the {@code Class} for type {@code T}
     * @param <T> the type to deserialize data into
     * @return an instance of {@code T}
     * @throws HankoClientException if any error during deserialization occurs
     */
    public <T> T deserialize(InputStream is, Class<T> valueType) {
        try {
            return mapper.readValue(is, valueType);
        } catch (IOException ex) {
            throw new HankoClientException(String.format(COULD_NOT_DESERIALIZE_FORMAT, ex.getMessage()), ex);
        }
    }

    /**
     * Deserialize String {@code s} into object of type {@code T}.
     * @param s the String
     * @param valueType the {@code Class} for type {@code T}
     * @param <T> the type to deserialize data into
     * @return an instance of {@code T}
     * @throws HankoClientException if any error during deserialization occurs
     */
    public <T> T deserialize(String s, Class<T> valueType) {
        try {
            return mapper.readValue(s, valueType);
        } catch (IOException ex) {
            throw new HankoClientException(String.format(COULD_NOT_DESERIALIZE_FORMAT, ex.getMessage()), ex);
        }
    }

    /**
     * Deserialize File {@code f} into object of type {@code T}.
     * @param f the File
     * @param valueType the {@code Class} for type {@code T}
     * @param <T> the type to deserialize data into
     * @return an instance of {@code T}
     * @throws HankoClientException if any error during deserialization occurs
     */
    public <T> T deserialize(File f, Class<T> valueType) {
        try {
            return mapper.readValue(f, valueType);
        } catch (IOException ex) {
            throw new HankoClientException(String.format(COULD_NOT_DESERIALIZE_FORMAT, ex.getMessage()), ex);
        }
    }

    /**
     * Deserialize InputStream {@code is} into a list of objects of type {@code T}.
     * @param is the InputStream
     * @param valueType the {@code Class} for type {@code T}
     * @param <T> the type to deserialize data into
     * @return an instance of {@code T}
     * @throws HankoClientException if any error during deserialization occurs
     */
    public <T> List<T> deserializeList(InputStream is, Class<T> valueType) {
        JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, valueType);
        try {
            return mapper.readValue(is, type);
        } catch (IOException ex) {
            throw new HankoClientException(String.format(COULD_NOT_DESERIALIZE_FORMAT, ex.getMessage()), ex);
        }
    }

    /**
     * Serialize instance of type {@code T} to a String.
     * @param value an instance of type {@code T}
     * @param <T> the type to serialize
     * @return the serialized String
     * @throws HankoClientException if any error during serialization occurs
     */
    public <T> String serialize(T value) {
        try {
            return mapper.writeValueAsString(value);
        } catch (IOException ex) {
            throw new HankoClientException("Could not serialize: " + ex.getMessage(), ex);
        }
    }
}
