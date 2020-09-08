package com.stevenpaligo.spacetrack.client.util;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DecimalToIntegerDeserializer extends StdDeserializer<Integer> {

  private static final long serialVersionUID = 1L;


  public DecimalToIntegerDeserializer() {
    this(null);
  }


  public DecimalToIntegerDeserializer(Class<?> clazz) {
    super(clazz);
  }


  @Override
  public Integer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

    String stringValue = jsonParser.getText();


    // decode the value
    return Double.valueOf(stringValue).intValue();
  }
}
