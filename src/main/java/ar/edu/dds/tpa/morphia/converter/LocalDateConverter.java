package ar.edu.dds.tpa.morphia.converter;


import static java.time.ZoneId.systemDefault;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;


public class LocalDateConverter extends TypeConverter implements SimpleValueConverter {

    public LocalDateConverter() {
        super(LocalDate.class);
    }

    @Override
    public Object decode(final Class<?> targetClass, final Object val, final MappedField optionalExtraInfo) {
        if (val == null) {
            return null;
        }

        if (val instanceof LocalDate) {
            return val;
        }

        if (val instanceof Date) {
            return LocalDateTime.ofInstant(((Date) val).toInstant(), ZoneId.systemDefault()).toLocalDate();
        }

        throw new IllegalArgumentException("No se pudo convertir " + val + " a LocalDate");
    }

    @Override
    public Object encode(final Object value, final MappedField optionalExtraInfo) {
        if (value == null) {
            return null;
        }
        LocalDate date = (LocalDate) value;
        return Date.from(date.atStartOfDay()
                             .atZone(systemDefault())
                             .toInstant());
    }
}
