package systems.getdata.example.xml.datastorage.api;

import systems.getdata.example.xml.datastorage.api.dto.Person;

import java.util.List;

public interface DataStorage {

  List<String> listIds();
  Person get(String id);
  void put(Person person);
}
