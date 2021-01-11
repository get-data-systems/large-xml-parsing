package systems.getdata.example.xml.dataingestion.xml.file;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class FileParser {

  public Stream<ByteArrayInputStream> extractNodes(InputStream is, String tagName) {
    NodeExtractor nodeExtractor = new NodeExtractor(is, tagName);
    NodeSource nodeSource = new NodeSource(nodeExtractor);
    return StreamSupport.stream(nodeSource, false);
  }
}
