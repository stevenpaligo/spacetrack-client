package com.stevenpaligo.spacetrack.client.util;

import java.io.IOException;
import java.util.Optional;
import org.threeten.extra.scale.UtcInstant;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class OptionalDateTimeToUtcInstantDeserializer extends StdDeserializer<Optional<UtcInstant>> {

  private static final long serialVersionUID = 1L;


  public OptionalDateTimeToUtcInstantDeserializer() {

    this(null);
  }


  public OptionalDateTimeToUtcInstantDeserializer(Class<Optional<UtcInstant>> clazz) {

    super(clazz);
  }


  @Override
  public Optional<UtcInstant> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

    return Optional.of(UtcInstantDeserializer.deserialize(jsonParser));
  }
}
