package info.jessetaina.alkkispro.model;

import org.springframework.data.repository.CrudRepository;

public interface SavedDrinkRepository extends CrudRepository<SavedDrink, Long> {
			 SavedDrink findById(long id);
}
