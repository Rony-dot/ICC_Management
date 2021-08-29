package com.rony.services;

import com.rony.HelperSingleton;
import com.rony.config.HibernateConfig;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.id.uuid.Helper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Properties;

public abstract class BaseService<T> {

    private SessionFactory sessionFactory;
    private Session session;
    private T model;

    HelperSingleton helper;

    public BaseService(T model){
        this.model = model;
        helper = HelperSingleton.getInstance();
    }

    private Session getSession(){
        try {
            helper.printMsg("getting current session");
            session = createAndGetLocalSessionFactoryBean().getCurrentSession();
        }catch (HibernateException he){
            he.printStackTrace();
            helper.printMsg("opening a new session");
            session = createAndGetLocalSessionFactoryBean().openSession();
        }
        return session;
    }

    private SessionFactory createAndGetLocalSessionFactoryBean() {
        if (this.sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = getBuiltProperties("hibernate.properties");

                configuration.setProperties(settings);
                configuration.addPackage("com.rony.models");
                configuration.addAnnotatedClass(model.getClass());
                StandardServiceRegistryBuilder serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(settings);
                sessionFactory = configuration.buildSessionFactory(serviceRegistry.build());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    private Properties getBuiltProperties(String propertyFileName) {
        Properties properties = new Properties();
        InputStream input = HibernateConfig.class
                .getClassLoader().getResourceAsStream(propertyFileName);
        try {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public Serializable save(T object) {
        var session = getSession();
        var tx = session.getTransaction();
        if(!tx.isActive()){
            tx = session.beginTransaction();
        }
        var result = session.save(object);
        session.flush();
        tx.commit();
        return result;
    }

    public <T> void delete(T object) {
        var session = getSession();
        var tx = session.getTransaction();
        if (!tx.isActive()) {
            tx = session.beginTransaction();
        }
        session.delete(object);
        session.flush();
        tx.commit();
    }

    public List<T> getAll(){
        var session = getSession();
        var tx = session.getTransaction();
        if (!tx.isActive()) {
            tx = session.beginTransaction();
        }
        var cb = getCriteriaBuilder();
        var cq = cb.createQuery(model.getClass());
        var root = cq.from(model.getClass());

        var result =  query(cq).getResultList();
        session.flush();
        tx.commit();

        return (List<T>) result;
    }

//    public T getById(String id){
//        var session = getSession();
//        var tx = session.getTransaction();
//        if (!tx.isActive()) {
//            tx = session.beginTransaction();
//        }
//        var cb = getCriteriaBuilder();
//        var cq = cb.createQuery(model.getClass());
//        var root = cq.from(model.getClass());
//        cq.where(cb.equal(root.get("id"),id));
//
//        var result =  query(cq).getSingleResult();
//
//        session.flush();
//        tx.commit();
//        return (model.getClass()) result;
//    }


    public CriteriaBuilder getCriteriaBuilder(){
        var session = getSession();
        var tx = session.getTransaction();
        if (!tx.isActive()) {
            tx = session.beginTransaction();
        }
        return session.getCriteriaBuilder();
    }

    <T> TypedQuery<T> query(CriteriaQuery<T> query){
        return   getSession()
                .getEntityManagerFactory()
                .createEntityManager()
                .createQuery(query);
    }


}
