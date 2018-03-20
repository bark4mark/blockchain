package co.howard.blockchain;

import java.util.Date;
import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Block {
  private Date timestamp;
  private Set<Transaction> transactions;
  private long proof;
  private String previousHash;
}
