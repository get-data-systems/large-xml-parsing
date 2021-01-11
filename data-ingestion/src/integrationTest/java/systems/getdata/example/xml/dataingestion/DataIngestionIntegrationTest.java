package systems.getdata.example.xml.dataingestion;

import org.junit.jupiter.api.Test;
import systems.getdata.example.xml.dataingestion.mapping.MappingConfiguration;
import systems.getdata.example.xml.dataingestion.xml.file.FileParserConfiguration;
import systems.getdata.example.xml.dataingestion.xml.node.NodeParserConfiguration;
import systems.getdata.example.xml.datastorage.api.DataStorage;
import systems.getdata.example.xml.datastorage.api.dto.Person;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.*;

public class DataIngestionIntegrationTest {

  private final DataStorage dataStorage = new InMemoryDataStorage();
  private final ParsePersonUseCase parsePersonUseCase = new ParsePersonUseCase(
      new FileParserConfiguration().nodeProvider(),
      dataStorage,
      new NodeParserConfiguration().xmlParser(),
      new MappingConfiguration().domainMapper());

  @Test
  public void shouldExtractMultiplePersonFromXmlFile() {
    InputStream personStream =
        this.getClass().getClassLoader().getResourceAsStream("Person.xml");

    parsePersonUseCase.parse(personStream);

    assertThat(dataStorage.get("1032")).isEqualTo(Person.builder()
        .id("1032")
        .fullName("John Doe")
        .build());

    assertThat(dataStorage.get("1099")).isEqualTo(Person.builder()
        .id("1099")
        .fullName("Catherine Boka")
        .build());
  }
}
