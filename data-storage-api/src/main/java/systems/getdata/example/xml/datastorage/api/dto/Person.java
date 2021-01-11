package systems.getdata.example.xml.datastorage.api.dto;

import lombok.Builder;
import lombok.Singular;
import lombok.ToString;
import lombok.Value;

import java.util.List;

@Value
@Builder
@ToString
public class Person {
  String id;
  String fullName;
}
