package tdog.lib.crawler;

import java.util.Properties;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.zaxxer.hikari.HikariDataSource;

import tdog.lib.SpringLib;
import tdog.lib.crawler.service.IService;

@Configuration
@ComponentScan(basePackages = { "tdog.lib.spring", "tdog.lib.crawler.service" })
@EnableJpaRepositories({ "tdog.lib.crawler.repository", "tdog.lib.crawler.repo" })
public class TrumwebCrawlerConfig {

	/* Default service */
	private static IService defaultService;

	public static IService getDefaultService() {
		return defaultService;
	}

	public static void setDefaultService(IService service) {
		TrumwebCrawlerConfig.defaultService = service;
	}

	/* Quickload */
	public static IService clearQuickloadDatabase() {
		return getService("root", "", "127.0.0.1", "3306", "quickload", false, true, 150);
	}

	public static IService getQuickloadService() {
		return getService("root", "", "127.0.0.1", "3306", "quickload", false, false, 150);
	}
	public static void useQuickloadService(){
		setDefaultService(getQuickloadService());
	}

	public static IService getService(String username, String password, String server, String port, String db,
			boolean showSql, boolean overwriteAll, int poolSize) {

		TrumwebCrawlerConfig.username = username;
		TrumwebCrawlerConfig.password = password;
		TrumwebCrawlerConfig.server = server;
		TrumwebCrawlerConfig.port = port;
		TrumwebCrawlerConfig.db = db;
		TrumwebCrawlerConfig.showSql = showSql;
		TrumwebCrawlerConfig.overwriteAll = overwriteAll;
		TrumwebCrawlerConfig.poolSize = poolSize;
		ApplicationContext context = SpringLib.loadContextFromClasses(TrumwebCrawlerConfig.class);
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
		bean.setPackagesToScan("tdog.lib.crawler.entity", "tdog.lib.crawler.table");
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
