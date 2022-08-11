package info.jessetaina.alkkispro.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PostFilter;

public interface DrinkRepository extends CrudRepository<Drink, Long> {
		 Drink findById(long id);

		 @PostFilter("filterObject.user == null or filterObject.user.getUsername == authentication.name")
		 List<Drink> findByIsDefault(boolean is_default);
}
