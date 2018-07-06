package guru.springframework.command.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import guru.springframework.command.UnitOfMeasureCommand;
import guru.springframework.model.UnitOfMeasure;
import lombok.Synchronized;

/*
 * As the other Converter classes, the aim of creating these components is to be able to bind a form with POJO.
 */
@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure>{
	
	@Override
	@Nullable
	@Synchronized
	public UnitOfMeasure convert(UnitOfMeasureCommand uomc) {
		if(uomc == null) {
			return null;
		}
		UnitOfMeasure uom = new UnitOfMeasure();
		uom.setId(uom.getId());
		uom.setMeasure(uom.getMeasure());
		return uom;
	}

}
