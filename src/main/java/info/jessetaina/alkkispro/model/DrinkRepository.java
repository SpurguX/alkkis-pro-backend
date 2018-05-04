package info.jessetaina.alkkispro.model;

import org.springframework.data.repository.CrudRepository;

public interface DrinkRepository extends CrudRepository<Drink, Long> {
		 Drink findById(long id);
}
