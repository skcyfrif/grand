package com.cyfrifpro.config;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//import com.cyfrifpro.model.EstimateRequirments;
//import com.cyfrifpro.request.EstimateRequestDTO;

@Configuration
public class AppConfig {

	@Bean
	 public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                   .setFieldMatchingEnabled(true)  // Enable field-level matching
                   .setPropertyCondition(Conditions.isNotNull());  // Ignore null properties
        return modelMapper;
    }
	
}
