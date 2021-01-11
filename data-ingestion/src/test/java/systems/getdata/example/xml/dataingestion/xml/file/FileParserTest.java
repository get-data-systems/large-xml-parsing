package systems.getdata.example.xml.dataingestion.xml.file;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.*;

class FileParserTest {

  private static final String Person1Xml = "<Person id=\"1\"></Person>";
  private static final String Person2Xml = "<Person id=\"2\"></Person>";

  private static final String sourceXml = ""
      + "<root>"
      + Person1Xml
      + "<Ignored></Ignored>"
      + Person2Xml
      + "</root>";

  private final FileParser fileParser = new FileParser();

  @Test
  void shouldSplitXmlIntoSinglePersons() {
    ByteArrayInputStream is = new ByteArrayInputStream(sourceXml.getBytes());

    List<String> collect = fileParser.extractNodes(is, "Person")
        .map(this::toString)
        .collect(toList());

    assertThat(collect).containsExactly(
        Person1Xml,
        Person2Xml);
  }

  private String toString(ByteArrayInputStream is) {
    return new String(is.readAllBytes(), StandardCharsets.UTF_8);
  }
}