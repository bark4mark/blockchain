package co.howard.blockchain;

import java.nio.charset.Charset;

import com.google.common.base.Charsets;

import co.howard.blockchain.web.Services;

public class Application {

  public static final Charset DEFAULT_CHARSET = Charsets.UTF_8;

  public static void main(String[] args) {
    Services services = new Services();
    services.routes();
  }

}
