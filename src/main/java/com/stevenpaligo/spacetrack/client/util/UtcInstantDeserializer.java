package com.stevenpaligo.spacetrack.client.util;

import java.io.IOException;
import org.threeten.extra.scale.UtcInstant;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class UtcInstantDeserializer extends StdDeserializer<UtcInstant> {

  private static final long serialVersionUID = 1L;


  public UtcInstantDeserializer() {

    this(null);
  }


  public UtcInstantDeserializer(Class<UtcInstant> clazz) {

    super(clazz);
  }


  @Override
  public UtcInstant deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

    return deserialize(jsonParser);
  }


  static UtcInstant deserialize(JsonParser jsonParser) throws IOException, JsonProcessingException {

    return UtcInstant.parse(jsonParser.getText().replace(' ', 'T') + 'Z');
  }
}
