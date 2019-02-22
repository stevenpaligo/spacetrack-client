package com.stevenpaligo.spacetrack.client.util;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class BooleanYesNoDeserializer extends StdDeserializer<Boolean> {

  private static final long serialVersionUID = 1L;


  public BooleanYesNoDeserializer() {
    this(null);
  }


  public BooleanYesNoDeserializer(Class<?> clazz) {
    super(clazz);
  }


  @Override
  public Boolean deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

    String stringValue = jsonParser.getText();


    // decode the value
    if (stringValue.equals("Y")) {
      return Boolean.TRUE;
    } else if (stringValue.equals("N")) {
      return Boolean.FALSE;
    } else {
      throw new IOException("Unsupported boolean representation: " + stringValue);
    }
  }
}
