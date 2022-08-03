package br.com.projeto.infra.jackson.ser;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import br.com.projeto.infra.utils.JavaTimeUtils;

public class LocalDateTimeSerializer extends StdSerializer<LocalDateTime> {

	private static final long serialVersionUID = 7132391551144136417L;

	public LocalDateTimeSerializer() {
		super(LocalDateTime.class);
	}

	@Override
	public void serialize(final LocalDateTime value, final JsonGenerator generator, final SerializerProvider provider)
			throws IOException {
		generator.writeString(value.format(JavaTimeUtils.LOCAL_DATE_TIME_FORMATTER));
	}
}