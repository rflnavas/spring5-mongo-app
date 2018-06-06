package guru.springframework.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import guru.springframework.model.Category;
import guru.springframework.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
	
	private CategoryRepository categoryRepository;
	
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Optional<Category> getCategoryByName(String name) {
		return categoryRepository.findByCategoryName(name);
	}

}
