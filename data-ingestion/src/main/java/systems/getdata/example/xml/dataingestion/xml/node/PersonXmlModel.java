package systems.getdata.example.xml.dataingestion.xml.node;

import lombok.Builder;
import lombok.Data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@Builder
@Data
@JsonDeserialize(builder = PersonXmlModel.PersonXmlModelBuilder.class)
@JacksonXmlRootElement(localName = "Person")
public class PersonXmlModel {

  @JacksonXmlProperty(localName = "id", isAttribute = true)
  String id;

  @JacksonXmlProperty(localName = "FirstName")
  String firstName;

  @JacksonXmlProperty(localName = "LastName")
  String lastName;
}
