package guru.springframework.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import guru.springframework.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, String>{
	Optional<Category> findByCategoryName(String description);
}
