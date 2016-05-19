package net.croz.osd.edu.conf;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class JdbcConfig {
	@Bean
    public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        EmbeddedDatabase db = builder
        	.setType(EmbeddedDatabaseType.DERBY)
            .addScript("net/croz/osd/edu/conf/create-db.sql")
            .addScript("net/croz/osd/edu/conf/insert-data.sql")
            .build();
        return db;
    }
}
