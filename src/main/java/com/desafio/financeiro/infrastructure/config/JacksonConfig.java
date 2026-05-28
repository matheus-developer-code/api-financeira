package com.desafio.financeiro.infrastructure.config;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Configuration
public class JacksonConfig {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonCustomizer() {
        return new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder builder) {
            	
                builder.serializerByType(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
                    @Override
                    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                        if (value != null) {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                            gen.writeString(value.format(formatter));
                        }
                    }
                });
                
                builder.deserializerByType(BigDecimal.class, new JsonDeserializer<BigDecimal>() {
                    @Override
                    public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
                        String value = p.getText();
                        if (value == null || value.isBlank()) {
                            return null;
                        }
                        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP);
                    }
                });

                builder.serializerByType(BigDecimal.class, new JsonSerializer<BigDecimal>() {
                    @Override
                    public void serialize(BigDecimal value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                        if (value != null) {
                            // Configura o formato monetário brasileiro (R$ 1.234,56)
                            DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
                            gen.writeString(df.format(value));
                        }
                    }
                });
            }
        };
    }
}