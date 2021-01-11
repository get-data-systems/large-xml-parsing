package systems.getdata.example.xml.dataingestion.xml.node;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class NodeParserConfiguration {

  XmlMapper xmlMapper() {
    XmlMapper xmlMapper = new XmlMapper();
    xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return xmlMapper;
  }

  public NodeParser xmlParser() {
    return new NodeParser(xmlMapper());
  }
}
