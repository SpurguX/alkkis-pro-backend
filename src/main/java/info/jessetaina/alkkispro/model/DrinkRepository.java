package info.jessetaina.alkkispro.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DrinkRepository extends CrudRepository<Drink, Long> {
		 Drink findById(long id);
		 List<Drink> findByIsDefault(boolean is_default);
}
