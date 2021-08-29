package com.rony.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;
import javax.persistence.Entity;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.InputStream;
import java.io.ObjectInputFilter;
import java.lang.reflect.Modifier;
import java.util.Properties;

public class HibernateConfig {

    private SessionFactory sessionFactory = null;
    private Session session;

    public Session getSession(){
        this.session = createAndGetLocalSessionFactoryBean().getCurrentSession();
        return session!=null? this.session : createAndGetLocalSessionFactoryBean().openSession();
    }

    public SessionFactory createAndGetLocalSessionFactoryBean() {
        if(this.sessionFactory==null){

            try{
                Configuration cfg = new Configuration();
                Properties setting = getBuiltProperties("hibernate.properties");
                cfg.setProperties(setting);
                cfg.addPackage("com.rony.models");
                for( Class<?> clazz : (new Reflections("com.rony.models")).getTypesAnnotatedWith(Entity.class) ) {
                    if (!Modifier.isAbstract(clazz.getModifiers())) {
                        cfg.addAnnotatedClass(clazz);
                    }
                }
                StandardServiceRegistryBuilder serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(setting);
                sessionFactory = cfg.buildSessionFactory(serviceRegistry.build());

            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    private Properties getBuiltProperties(String propertyFileName) {
        Properties properties = new Properties();
        InputStream inputStream = HibernateConfig.class
                .getClassLoader()
                .getResourceAsStream(propertyFileName);
        try{
            properties.load(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
        return properties;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        Session session = getSession();
        var tx = session.getTransaction();
        if (!tx.isActive()) {
            tx = session.beginTransaction();
        }
        return session.getCriteriaBuilder();
    }

    public <T> TypedQuery<T> query(CriteriaQuery<T> query) {
        var session = getSession();
        var tx = session.getTransaction();
        if (!tx.isActive()) {
            tx = session.beginTransaction();
        }
        var result = session.getEntityManagerFactory().createEntityManager().createQuery(query);
        tx.commit();
        return result;
    }

}
