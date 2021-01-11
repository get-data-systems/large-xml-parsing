package systems.getdata.example.xml.dataingestion.xml.node;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.InputStream;

@AllArgsConstructor
public class NodeParser {

  private final XmlMapper xmlMapper;

  @SneakyThrows
  public PersonXmlModel parsePersonNode(InputStream is) {
    return xmlMapper.readValue(is, PersonXmlModel.class);
  }
}
