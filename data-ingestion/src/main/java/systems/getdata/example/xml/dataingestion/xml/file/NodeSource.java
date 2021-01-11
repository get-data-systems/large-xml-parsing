package systems.getdata.example.xml.dataingestion.xml.file;

import java.io.ByteArrayInputStream;
import java.util.Optional;
import java.util.Spliterators.AbstractSpliterator;
import java.util.function.Consumer;

class NodeSource extends AbstractSpliterator<ByteArrayInputStream> {

  private final NodeExtractor nodeExtractor;

  NodeSource(NodeExtractor nodeExtractor) {
    super(-1, 0);
    this.nodeExtractor = nodeExtractor;
  }

  @Override
  public boolean tryAdvance(
      Consumer<? super ByteArrayInputStream> consumer) {
    Optional<ByteArrayInputStream> next = nodeExtractor.getNext();
    next.ifPresent(consumer);
    return next.isPresent();
  }
}
