package net.croz.osd.edu.conf;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@ComponentScan(basePackages = "net.croz.osd.edu.security")
public class SecurityConfig {
	@Autowired
	@Qualifier("propertiesFactoryBean")
	private Properties userProperties;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new InMemoryUserDetailsManager(userProperties); 
	}
}
