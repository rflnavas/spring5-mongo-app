package guru.springframework.command.converter;

import org.springframework.core.convert.converter.Converter;

import guru.springframework.command.UnitOfMeasureCommand;
import guru.springframework.model.UnitOfMeasure;

public class UnitOfMeasureConverter implements Converter<UnitOfMeasure, UnitOfMeasureCommand>{

	@Override
	public UnitOfMeasureCommand convert(UnitOfMeasure uom) {
		UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();
		uomc.setId(uom.getId());
		return null;
	}

}
