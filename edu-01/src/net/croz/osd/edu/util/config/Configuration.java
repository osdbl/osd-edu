package net.croz.osd.edu.util.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.croz.osd.edu.util.ExitStatus;

public class Configuration {
	public static Logger logger = Logger.getLogger(Configuration.class.getName());
	
	public static String locale;
	
	protected static Properties loadConfiguration(String path) {
		Properties props = new Properties();
		try (FileInputStream fis = new FileInputStream(path)) {
			props.load(fis);
			locale = props.getProperty("locale");
			logger.log(Level.CONFIG, "Loaded {0}" , path);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "{0} {1}", new Object[] { e.getMessage(), ". Terminated!"});
			System.exit(ExitStatus.INVALID_CONFIG.status);
		}
		return props;
	}
}
