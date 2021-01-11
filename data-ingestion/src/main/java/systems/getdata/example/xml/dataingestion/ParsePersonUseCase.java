package systems.getdata.example.xml.dataingestion;

import lombok.RequiredArgsConstructor;

import systems.getdata.example.xml.dataingestion.mapping.DomainMapper;
import systems.getdata.example.xml.dataingestion.xml.file.FileParser;
import systems.getdata.example.xml.dataingestion.xml.node.NodeParser;
import systems.getdata.example.xml.datastorage.api.DataStorage;

import java.io.InputStream;

@RequiredArgsConstructor
public class ParsePersonUseCase {

  private final FileParser fileParser;
  private final DataStorage dataStorage;
  private final NodeParser nodeParser;
  private final DomainMapper domainMapper;

  public void parse(InputStream is) {
    fileParser.extractNodes(is, "Person")
        .map(nodeParser::parsePersonNode)
        .map(domainMapper::toPerson)
        .forEach(dataStorage::put);
  }
}
