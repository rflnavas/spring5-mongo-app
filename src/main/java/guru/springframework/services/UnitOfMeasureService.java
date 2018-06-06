package guru.springframework.services;

import java.util.Optional;

import guru.springframework.model.UnitOfMeasure;

public interface UnitOfMeasureService {
	
	Optional<UnitOfMeasure> getUnitOfMeasureByName(String name);
	Iterable<UnitOfMeasure> getAll();
	
}
