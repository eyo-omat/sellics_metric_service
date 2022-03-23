package com.sellics.sellics_metric_service.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class SellicsDateSerializer extends JsonDeserializer<DateTime> {
    Logger logger = LoggerFactory.getLogger(SellicsDateSerializer.class);

    @Override
    public DateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String timestamp = jp.getText().trim();

        try {
            return new DateTime(Long.valueOf(timestamp + "000"));
        } catch (NumberFormatException e) {
            logger.warn("Unable to deserialize timestamp: " + timestamp, e);
            return null;
        }
    }

}
