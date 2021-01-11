package systems.getdata.example.xml.dataingestion.xml.file;

import lombok.SneakyThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Optional;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;

import static java.nio.charset.StandardCharsets.UTF_8;

class NodeExtractor {

  private final XMLEventReader eventReader;
  private final String tagName;

  @SneakyThrows
  protected NodeExtractor(InputStream inputStream, String tagName) {
    XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
    this.eventReader = xmlInputFactory.createXMLEventReader(inputStream);
    this.tagName = tagName;
  }

  @SneakyThrows
  public Optional<ByteArrayInputStream> getNext() {
    final ByteArrayOutputStream content = new ByteArrayOutputStream();
    try (final OutputStreamWriter writer = new OutputStreamWriter(content, UTF_8)) {
      int depth = 0;
      while (eventReader.hasNext()) {
        XMLEvent xmlEvent = eventReader.nextEvent();

        if (isStartTag(xmlEvent)) {
          ++depth;
        }

        if (depth > 0) {
          xmlEvent.writeAsEncodedUnicode(writer);
        }

        if (isEndTag(xmlEvent)) {
          --depth;

          if (depth <= 0) {
            writer.flush();
            return Optional.of(content)
                .map(ByteArrayOutputStream::toByteArray)
                .map(ByteArrayInputStream::new);
          }
        }
      }
    }

    return Optional.empty();
  }

  private boolean isStartTag(XMLEvent xmlEvent) {
    return xmlEvent.isStartElement()
        && tagName.equals(xmlEvent.asStartElement().getName().getLocalPart());
  }

  private boolean isEndTag(XMLEvent xmlEvent) {
    return xmlEvent.isEndElement()
        && tagName.equals(xmlEvent.asEndElement().getName().getLocalPart());
  }
}
