package br.com.cidade.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

public class MySqlDataSource {
	@Value("${mysqlds.dbDriver}")
	private String dbDriver;

	@Value("${mysqlds.dbUrl}")
	private String dbUrl;

	@Value("${mysqlds.dbUsername}")
	private String dbUsername;

	@Value("${mysqlds.dbPassword}")
	private String dbPassword;

	@Value("${mysqlds.dbPoolInitialSize}")
	private Integer dbPoolInitialSize;

	@Value("${mysqlds.dbPoolMaxActive}")
	private Integer dbPoolMaxActive;

	@Bean
	@Profile(Profiles.PRODUCTION)
	public BasicDataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(dbDriver);
		ds.setUrl(dbUrl);
		ds.setUsername(dbUsername);
		ds.setPassword(dbPassword);
		ds.setInitialSize(dbPoolInitialSize);
		ds.setMaxActive(dbPoolMaxActive);

		return ds;
	}

}
