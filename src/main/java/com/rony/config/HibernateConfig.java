package com.rony.config;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import java.io.InputStream;
import java.lang.reflect.Modifier;
import java.util.Properties;

//@Component
@EnableTransactionManagement
public class HibernateConfig {

    private SessionFactory sessionFactory = null;
    private Session session;

    public Session getSession(){
        this.session = createAndGetLocalSessionFactoryBean().getCurrentSession();
        if(session!=null){
            System.out.println("------------------------------------------------");
            System.out.println("giving existing session");
            return this.session;
        }else {
            System.out.println("------------------------------------------------");
            System.out.println("creating new session");
            return createAndGetLocalSessionFactoryBean().openSession();
        }
//        return session!=null? this.session : createAndGetLocalSessionFactoryBean().openSession();
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
        session.flush();
        tx.commit();
        return result;
    }

//    @Transactional
    public void saveObject(Object o){
        var session = getSession();
        var tx = session.getTransaction();
        if (!tx.isActive()) {
            tx = session.beginTransaction();
        }
        session.save(o);
        tx.commit();
    }

    public void updateObject(Object o){
        var session = getSession();
        var tx = session.getTransaction();
        if (!tx.isActive()) {
            tx = session.beginTransaction();
        }
        sessionFactory.getCurrentSession().merge(o);
        tx.commit();

    }


//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        final JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(getSession().getEntityManagerFactory());
//        return transactionManager;
//    }


}
