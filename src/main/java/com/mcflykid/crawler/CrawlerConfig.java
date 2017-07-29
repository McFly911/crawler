package com.mcflykid.crawler;

import java.util.Properties;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.mcflykid.crawler.jpa.IService;
import com.mcflykid.crawler.lib.SpringLib;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = { "com.mcflykid.crawler.jpa" })
@EnableJpaRepositories({ "com.mcflykid.crawler.jpa" })
public class CrawlerConfig {

	public static IService getService() {
		return loadService("root", "", "127.0.0.1", "3306", "crawler", false, 150, false);
	}

	public static void resetDatabase() {
		loadService("root", "", "127.0.0.1", "3306", "crawler", false, 150, true);
	}

	private static IService loadService(String username, String password, String server, String port, String db,
			boolean showSql, int poolSize, boolean overwriteAll) {

		CrawlerConfig.username = username;
		CrawlerConfig.password = password;
		CrawlerConfig.server = server;
		CrawlerConfig.port = port;
		CrawlerConfig.db = db;
		CrawlerConfig.showSql = showSql;
		CrawlerConfig.overwriteAll = overwriteAll;
		CrawlerConfig.poolSize = poolSize;
		ApplicationContext context = SpringLib.loadContextFromClasses(CrawlerConfig.class);
		return (IService) context.getBean(IService.class);
	}

	private HikariDataSource dataSource() {
		HikariDataSource o = new HikariDataSource();
		o.setJdbcUrl(String.format("jdbc:mysql://%s:%s/%s", server, port, db));
		o.setUsername(username);
		o.setPassword(password);
		o.setMaximumPoolSize(poolSize);
		o.setDriverClassName("com.mysql.cj.jdbc.Driver");
		return o;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
		bean.setDataSource(dataSource());
		bean.setPackagesToScan("com.mcflykid.crawler.jpa");
		Properties properties = new Properties();
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.hbm2ddl.auto", overwriteAll ? "create" : "validate");
		properties.setProperty("hibernate.show_sql", showSql ? "true" : "false");
		properties.setProperty("hibernate.format_sql", "true");
		bean.setJpaProperties(properties);
		bean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		return bean;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager bean = new JpaTransactionManager();
		bean.setEntityManagerFactory(entityManagerFactory().getObject());
		return bean;
	}

	private static String server;
	private static String port;
	private static String username;
	private static String password;
	private static String db;
	private static boolean showSql;
	private static boolean overwriteAll;
	private static int poolSize;

}
