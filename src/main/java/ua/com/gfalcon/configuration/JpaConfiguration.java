package ua.com.gfalcon.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ua.com.gfalcon.configuration.enums.hibernate.Hbm2ddl;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * @author Oleksii Khalikov
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
@EnableJpaRepositories(basePackages = "ua.com.gfalcon")
@EnableTransactionManagement
public class JpaConfiguration {


    @Autowired
    private Environment environment;

    @Bean(name = "dataSourceProperties")
    @Primary
    @ConfigurationProperties(prefix = "dataSource")
    public DataSourceProperties dataSourceProperties() {
        DataSourceProperties dataSourceProperties = new DataSourceProperties();
        String sDATABASE_URL = System.getenv("DATABASE_URL");
        if (sDATABASE_URL == null || sDATABASE_URL.isEmpty() || sDATABASE_URL.equals("")) {
            sDATABASE_URL = "postgres://postgres:postgres@localhost:5432/gesem";
        }
        String driverClassName = "org.postgresql.Driver";
        dataSourceProperties.setDriverClassName(driverClassName);
        try {
            URI dbUrl = new URI(sDATABASE_URL);
            String url = "jdbc:postgresql://" + dbUrl.getHost() + ":" + dbUrl.getPort() + dbUrl.getPath();
            dataSourceProperties.setUrl(url);
            String username = dbUrl.getUserInfo().split(":")[0];
            dataSourceProperties.setUsername(username);
            String pass = dbUrl.getUserInfo().split(":")[1];
            dataSourceProperties.setPassword(pass);
            return dataSourceProperties;
        } catch (URISyntaxException e) {
            System.out.println("e = " + e.getMessage());
            return new DataSourceProperties();
        } catch (Exception e2) {
            System.out.println("e2 =" + e2.getMessage());
            return new DataSourceProperties();
        }
    }


    @Bean
    public DataSource dataSource() {
        DataSourceProperties properties = dataSourceProperties();
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        return dataSource;
    }


    /*
     * Entity Manager Factory setup.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("ua.com.gfalcon");
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties());
        return factoryBean;
    }

    /*
     * Provider specific adapter.
     */
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
        return hibernateJpaVendorAdapter;
    }

    /*
     * Here you can specify any provider specific properties.
     */
    private Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect");
        properties.put("hibernate.hbm2ddl.auto", Hbm2ddl.UPDADE.getValue());
        properties.put("hibernate.show_sql", true);
        //properties.put("hibernate.format_sql", true);
        properties.put("hibernate.use_sql_comments", true);
        properties.put("hibernate.enable_lazy_load_no_trans", true);
        return properties;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
    }

}
