package systems.getdata.example.xml.dataingestion.xml.node;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.*;

class NodeParserTest {

  private static final String idXml = ""
      + "<Person id=\"1\">\n"
      + "</Person>";

  private static final String nameXml = ""
      + "<Person>\n"
      + "  <FirstName>John</FirstName>\n"
      + "  <LastName>Doe</LastName>\n"
      + "</Person>\n";

  private final NodeParser nodeParser = new NodeParserConfiguration().xmlParser();

  @Test
  public void shouldParseId() {
    ByteArrayInputStream is = new ByteArrayInputStream(idXml.getBytes());

    PersonXmlModel parse = nodeParser.parsePersonNode(is);

    assertThat(parse).isEqualTo(PersonXmlModel.builder()
        .id("1")
        .build());
  }

  @Test
  public void shouldParseName() {
    ByteArrayInputStream is = new ByteArrayInputStream(nameXml.getBytes());

    PersonXmlModel parse = nodeParser.parsePersonNode(is);

    assertThat(parse).isEqualTo(PersonXmlModel.builder()
        .firstName("John")
        .lastName("Doe")
        .build());
  }
}