package systems.getdata.example.xml.dataingestion.mapping;

import systems.getdata.example.xml.dataingestion.xml.node.PersonXmlModel;
import systems.getdata.example.xml.datastorage.api.dto.Person;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DomainMapperTest {

  private final DomainMapper domainMapper = new MappingConfiguration().domainMapper();

  @Test
  public void shouldMapFields() {
    PersonXmlModel person = PersonXmlModel.builder()
        .id("1")
        .firstName("John")
        .lastName("Doe")
        .build();

    Person result = domainMapper.toPerson(person);

    assertThat(result).isEqualTo(Person.builder()
        .id("1")
        .fullName("John Doe")
        .build());
  }

  @Test
  public void shouldThrowIfIdIsMissing() {
    PersonXmlModel emptyModel = PersonXmlModel.builder().build();

    assertThatThrownBy(() -> domainMapper.toPerson(emptyModel))
        .isInstanceOf(RuntimeException.class)
        .hasMessageContaining("id");
  }

  @Test
  public void shouldThrowIfFirstNameIsMissing() {
    PersonXmlModel emptyModel = PersonXmlModel.builder()
        .id("id")
        .build();

    assertThatThrownBy(() -> domainMapper.toPerson(emptyModel))
        .isInstanceOf(RuntimeException.class)
        .hasMessageContaining("firstName");
  }
}