package guru.springframework.command.converter;

import org.springframework.core.convert.converter.Converter;

import guru.springframework.command.CategoryCommand;
import guru.springframework.model.Category;

public class CategoryConverter implements Converter<CategoryCommand, Category>{

	@Override
	public Category convert(CategoryCommand cc) {
		
		return null;
	}

	
}
