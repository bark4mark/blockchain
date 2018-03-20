package co.howard.blockchain;

import java.util.Deque;

public interface Blockchain {

  /**
   * Creates a block, adding it to the chain
   * 
   * @param previousHash The hash of the previous block
   * @return The created block
   */
  Block createBlock(final String previousHash);

  /**
   * Queues a transaction
   */
  void queueTransaction(final Transaction transaction);

  /**
   * Creates a hash from a block
   * 
   * @param block The block to hash
   * @return The generated hash
   */
  String generateBlockHash(final Block block);

  /**
   * Returns the last block in the chain
   * 
   * @return
   */
  Block lastBlock();

  boolean validateBlock(final Block block);

  long proofOfWork(final Block block);

  /**
   * Gets the genesis block
   * 
   * @return The block
   */
  Block getGenesis();

  /**
   * Returns the current blockchain
   * 
   * @return The blockchain
   */
  Deque<Block> getChain();

  /**
   * Returns the system wallet address
   * 
   * @return The system wallet address
   */
  String getSystemWallet();
}
