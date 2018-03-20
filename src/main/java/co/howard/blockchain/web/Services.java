package co.howard.blockchain.web;

import java.util.Optional;

import co.howard.blockchain.Block;
import co.howard.blockchain.Blockchain;
import co.howard.blockchain.DefaultBlockchain;
import co.howard.blockchain.Transaction;
import spark.Spark;

public class Services {
  private final Blockchain blockchain = new DefaultBlockchain();
  private final DefaultResponseTransformer responseTransformer = new DefaultResponseTransformer();

  public void routes() {
    Spark.get("/mine", (request, response) -> {
      Block lastBlock = blockchain.lastBlock();
      Transaction transaction = Transaction.builder().payload("")
          .sender(blockchain.getSystemWallet()).recipient(Optional.empty()).build();
      blockchain.queueTransaction(transaction);
      String previousHash = blockchain.generateBlockHash(lastBlock);
      Block block = blockchain.createBlock(previousHash);
      return MiningResponse.builder().message("New block added to chain").previousHash(previousHash)
          .proof(block.getProof()).transactions(block.getTransactions()).build();
    }, responseTransformer);

    Spark.post("/transactions", (request, response) -> {
      String json = request.body();
      Transaction transaction = responseTransformer.render(json, Transaction.class);
      blockchain.queueTransaction(transaction);
      return "Transaction has been queued";
    }, responseTransformer);

    Spark.get("/chain", (request, response) -> {
      return blockchain.getChain();
    }, responseTransformer);


  }


}
