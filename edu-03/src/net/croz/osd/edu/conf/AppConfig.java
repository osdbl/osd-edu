package net.croz.osd.edu.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;

@Configuration
@PropertySource("classpath:/net/croz/osd/edu/conf/app.properties")
@ComponentScan(basePackages = "net.croz.osd.edu")
public class AppConfig {
	@Autowired
    Environment env;
	
	@Bean
	public PropertiesFactoryBean propertiesFactoryBean() {
		PropertiesFactoryBean propertiesFactoryBean = new PropertiesFactoryBean();
		propertiesFactoryBean.setLocation(new FileSystemResource(env.getProperty("users.location")));
		return propertiesFactoryBean;
	}
}
