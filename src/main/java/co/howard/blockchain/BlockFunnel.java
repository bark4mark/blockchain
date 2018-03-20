package co.howard.blockchain;

import com.google.common.hash.Funnel;
import com.google.common.hash.PrimitiveSink;

public class BlockFunnel implements Funnel<Block> {

  private static final long serialVersionUID = 1L;

  @Override
  public void funnel(Block block, PrimitiveSink sink) {
    sink.putLong(block.getTimestamp().getTime());
    sink.putString(block.getPreviousHash(), Application.DEFAULT_CHARSET);
  }

}
