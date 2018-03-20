package co.howard.blockchain.web;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import spark.ResponseTransformer;

public class DefaultResponseTransformer implements ResponseTransformer {
  private final ObjectMapper objectMapper = new ObjectMapper();
  private final Logger log = LoggerFactory.getLogger(getClass());

  @Override
  public String render(final Object toRender) throws Exception {
    String returnValue = objectMapper.writeValueAsString(toRender);
    log.debug("The return value is {}", returnValue);
    return returnValue;
  }

  public <Type> Type render(final String json, final Class<Type> clazz) throws IOException {
    return objectMapper.readValue(json, clazz);
  }

}
