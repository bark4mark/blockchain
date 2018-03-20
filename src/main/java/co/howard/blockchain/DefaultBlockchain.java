package co.howard.blockchain;

import java.util.Date;
import java.util.Deque;
import java.util.Set;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class DefaultBlockchain implements Blockchain {
  private final Set<Transaction> transactions = Sets.newHashSet();
  private final Deque<Block> chain = Lists.newLinkedList();
  private final BlockFunnel blockFunnel = new BlockFunnel();
  private final Block genesis = createBlock("abcd");

  private static final String VALID = "00";
  private final static HashFunction FUNCTION = Hashing.sha256();
  private static final String SYSTEM_WALLET =
      FUNCTION.newHasher().putString("SYSTEM", Charsets.UTF_8).hash().toString();

  @Override
  public void queueTransaction(final Transaction transaction) {
    this.transactions.add(transaction);
  }

  @Override
  public String generateBlockHash(final Block block) {
    return FUNCTION.newHasher().putLong(block.getTimestamp().getTime()).putLong(block.getProof())
        .hash().toString();
  }

  @Override
  public Block lastBlock() {
    return chain.peek();
  }

  @Override
  public Block createBlock(final String previousHash) {
    Set<Transaction> currentTransactions = Sets.newHashSet(this.transactions);
    this.transactions.clear();
    Date now = new Date();
    Block block = Block.builder().timestamp(now).transactions(currentTransactions)
        .previousHash(previousHash).build();
    block.setProof(proofOfWork(block));
    this.chain.push(block);
    return block;
  }

  @Override
  public Block getGenesis() {
    return this.genesis;
  }

  @Override
  public Deque<Block> getChain() {
    return this.chain;
  }

  @Override
  public String getSystemWallet() {
    return SYSTEM_WALLET;
  }

  @Override
  public boolean validateBlock(final Block block) {
    String hash = Hashing.sha256().newHasher().putObject(block, blockFunnel)
        .putLong(block.getProof()).hash().toString();
    return hash.endsWith(VALID);
  }

  @Override
  public long proofOfWork(final Block block) {
    long proof = 0;
    String hash =
        FUNCTION.newHasher().putObject(block, blockFunnel).putLong(proof).hash().toString();

    while (!hash.endsWith(VALID)) {
      proof++;
      hash = FUNCTION.newHasher().putObject(block, blockFunnel).putLong(proof).hash().toString();
    }
    return proof;
  }
}
