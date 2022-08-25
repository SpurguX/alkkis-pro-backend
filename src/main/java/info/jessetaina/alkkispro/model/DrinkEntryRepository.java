package info.jessetaina.alkkispro.model;

import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PostFilter;

public interface DrinkEntryRepository extends CrudRepository<DrinkEntry, Long>{

  // XXX PostFilter might end up having performance issues when there is more data in the DB
  // although the generated SQL seemed fine at a quick glance
  @PostFilter("filterObject.user.getUsername == authentication.name")
  List<DrinkEntry> findAll();
  List<DrinkEntry> findAllByDrinkingDate(Date drinkDate);
}
