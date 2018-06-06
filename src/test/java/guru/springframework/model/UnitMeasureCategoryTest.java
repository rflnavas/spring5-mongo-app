package guru.springframework.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.StreamSupport;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import guru.springframework.services.CategoryServiceImpl;
import guru.springframework.services.UnitOfMeasureServiceImpl;
import guru.springframework.utils.CollectionUtils;

public class UnitMeasureCategoryTest {

	UnitOfMeasureServiceImpl unitOfMeasureService;
	CategoryServiceImpl categoryService;
	@Mock
	UnitOfMeasureRepository unitOfMeasureRepository;
	@Mock
	CategoryRepository categoryRepository;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		unitOfMeasureService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository);
		categoryService = new CategoryServiceImpl(categoryRepository);
	}
	
	@Test
	public void test() {
		//System.out.println(unitOfMeasureService.count() + " Recipes");
		CollectionUtils.toStream(unitOfMeasureService.getAll(), false).forEach((x)->System.out.println(x));
		Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureService.getUnitOfMeasureByName("Tea spoon");
		
		Optional<Category> category = categoryRepository.findByCategoryName("Spanish");
//		/*
//		 * This is the way we convert a stream from an Iterable<T>
//		 */
		StreamSupport.stream(unitOfMeasureService.getAll().spliterator(), false).forEach((uom) -> System.out.println(uom));
		System.out.println("=================");
		
		Set<UnitOfMeasure> measures = new LinkedHashSet<>();
		unitOfMeasureService.getAll().iterator().forEachRemaining(u -> measures.add(u));
		assertEquals(true, category.isPresent());
		assertThat(measures.size() > 3);
	}

}
