package ru.hh.school.checkupextension.utils.data;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HibernateConfigurator {

    public static SessionFactory createSessionFactory(String propertiesResourceName, List<Class<?>> entitiesClass) {
        var serviceRegistry = new StandardServiceRegistryBuilder()
                .loadProperties(propertiesResourceName)
                .build();
        var metadataSources = new MetadataSources(serviceRegistry);
        entitiesClass.forEach(metadataSources::addAnnotatedClass);

        return metadataSources.buildMetadata().buildSessionFactory();
    }
}
