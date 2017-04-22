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
@EnableJpaRepositories(basePackages = "ua.com.gfalcon.gesem.dao")
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
        System.out.println("sDATABASE_URL = " + sDATABASE_URL);
        String driverClassName = "org.postgresql.Driver";
        dataSourceProperties.setDriverClassName(driverClassName);
        System.out.println(driverClassName);
        try {
            URI dbUrl = new URI(sDATABASE_URL);
            System.out.println("dbUrl created ");
            String url = "jdbc:postgresql://" + dbUrl.getHost() + ":" + dbUrl.getPort() + dbUrl.getPath();
            dataSourceProperties.setUrl(url);
            System.out.println("url = " + url);
            String username = dbUrl.getUserInfo().split(":")[0];
            dataSourceProperties.setUsername(username);
            System.out.println("username = " + username);
            String pass = dbUrl.getUserInfo().split(":")[1];
            dataSourceProperties.setPassword(pass);
            System.out.println("pass = " + pass);
            System.out.println("dataSourceProperties created ");
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
        System.out.println("dataSource inits ");
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());
        System.out.println("dataSource configured ");
        return dataSource;
    }


    /*
     * Entity Manager Factory setup.
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("ua.com.gfalcon.gesem");
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        //factoryBean.setJpaProperties(jpaProperties());
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
        //properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("datasource.hibernate.hbm2ddl.method"));
        //properties.put("hibernate.show_sql", environment.getRequiredProperty("datasource.hibernate.show_sql"));
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
