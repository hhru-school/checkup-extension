package ru.hh.school.checkupextension.config;

import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ru.hh.school.checkupextension.core.ContestApiResource;
import ru.hh.school.checkupextension.core.ContestService;
import ru.hh.school.checkupextension.test.TestDao;
import ru.hh.school.checkupextension.test.TestEntity;
import ru.hh.school.checkupextension.test.TestResource;
import ru.hh.school.checkupextension.test.TestService;
import ru.hh.school.checkupextension.utils.data.TransactionHelper;
import ru.hh.school.checkupextension.utils.stub.CheckupApiStub;

import java.util.List;

import static ru.hh.school.checkupextension.utils.data.HibernateConfigurator.createSessionFactory;

@Configuration
@Import({
        // TODO: Add dependencies
        ContestService.class,
        CheckupApiStub.class,
        TransactionHelper.class,

        // TODO: Remove test dependencies
        TestDao.class,
        TestService.class
})
public class AppConfiguration {
    @Bean
    public ResourceConfig jerseyConfig() {
        var config = new ResourceConfig();

        // TODO: Add resources
        config.register(ContestApiResource.class);

        // TODO: Remove test resources
        config.register(TestResource.class);

        return config;
    }

//    @Bean
//    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//        return new HibernateTransactionManager(sessionFactory);
//    }

    @Bean
    public SessionFactory sessionFactory() {
        List<Class<?>> entities = List.of(
            // TODO: Remove TestEntity
            TestEntity.class

            // TODO: Add entities
        );

        return createSessionFactory("hibernate.properties", entities);
    }
}
