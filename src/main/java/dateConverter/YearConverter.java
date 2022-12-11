package dateConverter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Year;

@Converter(autoApply = true)
public class YearConverter implements AttributeConverter<Year, Short> {

    public Short convertToDatabaseColumn(Year year) {
        if(year != null){
            return (short) year.getValue();
        }
        return null;
    }

    public Year convertToEntityAttribute(Short num) {
        if(num != null){
            return Year.of(num);
        }
        return null;
    }
}
