package br.com.gabriel.configuration;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.logging.Logger;

@Configuration
public class ModelMapperConfig {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Bean
    public ModelMapper modelMapper() {
        logger.info("Iniclizado ModelMapper - API");

        final ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

}
