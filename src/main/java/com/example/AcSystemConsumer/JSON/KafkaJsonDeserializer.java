package com.example.AcSystemConsumer.JSON;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;


public class KafkaJsonDeserializer<T> implements Deserializer<T> {
    private ObjectMapper objectMapper;
    private Class<T> tClass;

    public KafkaJsonDeserializer() {
    }

    public KafkaJsonDeserializer(ObjectMapper objectMapper, Class<T> tClass){
        this.objectMapper = objectMapper;
        this.tClass = tClass;
    }


    @Override
    public T deserialize(String topic, byte[] data) {
        if(data == null || data.length == 0) return null;
        try{
            return objectMapper.readValue(data, tClass);
        }
        catch (JacksonException e){
            throw new SerializationException("Deserialization failed", e);
        }
    }
}
