package de.andrena.et14.spring.webapp;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// TODO de.andrena.et14.spring.*.springconfig
@ComponentScan("de.andrena.*.springconfig")
public class WebAppConfiguration {

}
