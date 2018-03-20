package co.howard.blockchain;

import java.util.Optional;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Transaction {
  private String sender;
  private Optional<String> recipient;
  private String payload;
}
