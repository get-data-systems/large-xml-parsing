package systems.getdata.example.xml.dataingestion.mapping;

import lombok.AllArgsConstructor;

import systems.getdata.example.xml.dataingestion.xml.node.PersonXmlModel;
import systems.getdata.example.xml.datastorage.api.dto.Person;

import java.util.Optional;

@AllArgsConstructor
public class DomainMapper {

  public Person toPerson(PersonXmlModel personXmlModel) {
    String id = Optional.ofNullable(personXmlModel)
        .map(PersonXmlModel::getId)
        .orElseThrow(() -> new RuntimeException(
            "Entry is missing mandatory field: id"));

    String firstName = Optional.ofNullable(personXmlModel)
        .map(PersonXmlModel::getFirstName)
        .orElseThrow(() -> new RuntimeException(
            String.format("Entry with %s id is missing mandatory field: firstName", id)));

    String lastName = Optional.ofNullable(personXmlModel)
        .map(PersonXmlModel::getLastName)
        .orElseThrow(() -> new RuntimeException(
            String.format("Entry with %s id is missing mandatory field: lastName", id)));

    return Person.builder()
        .id(id)
        .fullName(String.format("%s %s", firstName, lastName))
        .build();
  }
}
