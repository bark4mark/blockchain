package co.howard.blockchain.web;

import java.util.Set;

import co.howard.blockchain.Transaction;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MiningResponse {
  private String message;
  private Set<Transaction> transactions;
  private long proof;
  private String previousHash;
}
