package com.example.AcSystemConsumer.DTO;

public record Message<T>(String topic, T data) { }
