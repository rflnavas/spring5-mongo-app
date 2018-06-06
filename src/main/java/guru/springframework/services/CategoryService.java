package guru.springframework.services;

import java.util.Optional;

import guru.springframework.model.Category;

public interface CategoryService {
	
	Optional<Category> getCategoryByName(String name);
}
