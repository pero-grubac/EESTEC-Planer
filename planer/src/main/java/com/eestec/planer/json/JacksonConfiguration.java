package com.eestec.planer.json;

import com.eestec.planer.dto.AdminDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.setMixInAnnotation(AdminDTO.class, AdminDTOMixin.class);
        mapper.registerModule(module);
        return mapper;
    }
}
