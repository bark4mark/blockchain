package co.howard.blockchain;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BlockchainTest {
  private Blockchain blockchain;

  @Before
  public void setUp() {
    this.blockchain = new DefaultBlockchain();
  }

  @Test
  public void shouldGenerateAndValidateProofOfWork() {
    Block block = Block.builder().previousHash("abcd").timestamp(new Date()).build();
    block.setProof(blockchain.proofOfWork(block));
    Assert.assertTrue(blockchain.validateBlock(block));
  }
}
