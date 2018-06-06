package guru.springframework.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import guru.springframework.model.UnitOfMeasure;
import guru.springframework.repositories.UnitOfMeasureRepository;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {
	
	private UnitOfMeasureRepository unitOfMeasureRepository;

	public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository) {
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}

	@Override
	public Optional<UnitOfMeasure> getUnitOfMeasureByName(String name) {
		return unitOfMeasureRepository.findByMeasure(name);
	}

	@Override
	public Iterable<UnitOfMeasure> getAll() {
		return unitOfMeasureRepository.findAll();
	}

}
