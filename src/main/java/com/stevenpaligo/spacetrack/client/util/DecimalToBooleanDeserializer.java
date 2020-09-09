package com.stevenpaligo.spacetrack.client.util;

import java.io.IOException;
import java.math.BigDecimal;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DecimalToBooleanDeserializer extends StdDeserializer<Boolean> {

  private static final long serialVersionUID = 1L;


  public DecimalToBooleanDeserializer() {

    this(null);
  }


  public DecimalToBooleanDeserializer(Class<?> clazz) {

    super(clazz);
  }


  @Override
  public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

    return (jsonParser.getDecimalValue().compareTo(BigDecimal.ZERO) != 0); // 0.0 = false, anything else = true
  }
}
