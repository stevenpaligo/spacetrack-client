package com.stevenpaligo.spacetrack.client.util;

import java.io.IOException;
import java.util.Optional;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class OptionalTinyIntToBooleanDeserializer extends StdDeserializer<Optional<Boolean>> {

  private static final long serialVersionUID = 1L;


  public OptionalTinyIntToBooleanDeserializer() {

    this(null);
  }


  public OptionalTinyIntToBooleanDeserializer(Class<?> clazz) {

    super(clazz);
  }


  @Override
  public Optional<Boolean> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

    return Optional.of(jsonParser.getIntValue() != 0); // 0 = false, anything else = true
  }
}
