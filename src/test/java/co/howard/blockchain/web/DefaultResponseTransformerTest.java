package co.howard.blockchain.web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import co.howard.blockchain.Blockchain;
import co.howard.blockchain.DefaultBlockchain;
import spark.ResponseTransformer;

public class DefaultResponseTransformerTest {
  private Blockchain blockchain;
  private ResponseTransformer responseTransformer;

  @Before
  public void setUp() {
    this.blockchain = new DefaultBlockchain();
    this.responseTransformer = new DefaultResponseTransformer();
  }

  @Test
  public void shouldConvertChainToJson() throws Exception {
    String json = this.responseTransformer.render(blockchain.getChain());
    Assert.assertNotNull(json);
  }

}
