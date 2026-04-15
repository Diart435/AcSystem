package com.example.AcSystemProducer.DTO;

public record Message<T>(String topic, T data) { }
