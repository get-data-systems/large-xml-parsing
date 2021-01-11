package systems.getdata.example.xml.dataingestion;

import systems.getdata.example.xml.datastorage.api.DataStorage;
import systems.getdata.example.xml.datastorage.api.dto.Person;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryDataStorage implements DataStorage {

  private final ConcurrentHashMap<String, Person> db = new ConcurrentHashMap<>();

  public void put(Person person) {
    String id = Optional.ofNullable(person)
        .map(Person::getId)
        .orElseThrow(() -> new IllegalArgumentException("Cannot add person with null id"));

    db.put(id, person);
  }

  @Override
  public Person get(String id) {
    return db.get(id);
  }

  @Override
  public List<String> listIds() {
    return Collections.list(db.keys());
  }
}
