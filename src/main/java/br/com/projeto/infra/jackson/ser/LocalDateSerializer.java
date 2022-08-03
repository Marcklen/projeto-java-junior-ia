package br.com.projeto.infra.jackson.ser;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import br.com.projeto.infra.utils.JavaTimeUtils;

public class LocalDateSerializer extends StdSerializer<LocalDate> {

	private static final long serialVersionUID = -6835630121118914170L;

	public LocalDateSerializer() {
		super(LocalDate.class);
	}

	@Override
	public void serialize(final LocalDate value, final JsonGenerator generator, final SerializerProvider provider)
			throws IOException {
		generator.writeString(value.format(JavaTimeUtils.LOCAL_DATE_FORMATTER));
	}
}