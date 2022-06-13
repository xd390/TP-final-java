package nc.univ.edt;

package nc.univ.springdata;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement // active le management des transaction
@ComponentScan(basePackages = { "nc.univ.springdata.service" })
@EnableJpaRepositories(basePackages = { "nc.univ.springdata.dao" }) // active les repositories
public class Config {

    /**
     * Retourne le bean correspondant a la source
     * de données cablé sur la base H2
     *
     * pour un serveur h2 : dataSource.setUrl("jdbc:h2:tcp://localhost/~/test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
     *
     * pour un fichier local : dataSource.setUrl("jdbc:h2:C:/Users/matma/test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
     *
     * @return DataSource
     */
    @Bean
    public DataSource dataSource() {

        // Declaration du dataSource avec le driver et le login/password
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        dataSource.setUrl("jdbc:h2:mem:AZ;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
        return dataSource;
    }

    /**
     * Retourne la fabrique d'entityManager
     *
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        // Creation de la factory
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

        // Configuration du datasource
        em.setDataSource(dataSource());

        // Configuration des modeles a analyser
        em.setPackagesToScan(new String[] { "nc.univ.springdata.model" });

        // Configuration du JPA implementation (Hibernate)
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        // Ajout des propriété du JPA Impl (config hibernate)
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        em.setJpaProperties(properties);

        return em;
    }

    /**
     * Retourne le bean correspondant au manager
     * des transaction
     *
     * @param emf
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

}
