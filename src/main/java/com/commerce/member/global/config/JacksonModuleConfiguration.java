package com.commerce.member.global.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;

@Configuration
public class JacksonModuleConfiguration implements JacksonModuleRegistrar {

    @Override
    public void maybeRegisterModule(ObjectMapper mapper) {
        mapper.registerModule(new JavaTimeModule());
    }
}
