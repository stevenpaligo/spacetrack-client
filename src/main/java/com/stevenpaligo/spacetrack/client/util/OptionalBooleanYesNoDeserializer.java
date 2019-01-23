package com.stevenpaligo.spacetrack.client.util;

import java.io.IOException;
import java.util.Optional;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class OptionalBooleanYesNoDeserializer extends StdDeserializer<Optional<Boolean>> {

  private static final long serialVersionUID = 1L;


  public OptionalBooleanYesNoDeserializer() {
    this(null);
  }


  public OptionalBooleanYesNoDeserializer(Class<?> clazz) {
    super(clazz);
  }


  @Override
  public Optional<Boolean> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

    String stringValue = jsonParser.getValueAsString();


    // decode the value
    if (stringValue.equals("Y")) {
      return Optional.of(Boolean.TRUE);
    } else if (stringValue.equals("N")) {
      return Optional.of(Boolean.FALSE);
    } else {
      throw new IOException("Unsupported boolean representation: " + stringValue);
    }
  }
}
