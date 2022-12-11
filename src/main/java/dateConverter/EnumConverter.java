package dateConverter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EnumConverter implements AttributeConverter<Rating, String> {

    public String convertToDatabaseColumn(Rating rating) {
        return rating.getValue();
    }

    public Rating convertToEntityAttribute(String dbData) {
        Rating[] ratings = Rating.values();
        for (Rating r : ratings) {
            if(r.getValue().equals(dbData)) return r;
        }
        return null;
    }
}
