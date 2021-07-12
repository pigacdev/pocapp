package hr.fina.eracun.application_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@PropertySources({ @PropertySource(value = "${url/eracun-ws-config}", ignoreResourceNotFound = true),
		@PropertySource(value = "file:${eracun-config}", ignoreResourceNotFound = true) })
public class ApplicationConfig {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {

		return new PropertySourcesPlaceholderConfigurer();
	}

	public static ApplicationConfig iApplicationConfig = null;

	public ApplicationConfig() {

		iApplicationConfig = ApplicationConfig.this;
	}

	@Autowired
	private Environment iEnv;

	/**
	 * @param pKey
	 * @return
	 */
	public static String getProperty(final String pKey) {

		return iApplicationConfig.getValue(pKey);
	}

	public static String[] getPropertyList(final String pKey) {

		final String tProperty = getProperty(pKey);
		if (tProperty == null || tProperty.isEmpty() || tProperty.trim().isEmpty()) {
			return null;
		}
		return tProperty.split(";");
	}

	/**
	 * @param pKey
	 * @return
	 */
	private String getValue(final String pKey) {

		return iEnv.getProperty(pKey);
	}

}
