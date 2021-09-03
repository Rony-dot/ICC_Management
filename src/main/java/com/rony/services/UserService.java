package com.rony.services;

import com.rony.config.HibernateConfig;
import com.rony.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService{

    @Autowired
    private HibernateConfig hibernateConfig;


    private List<User> userList;
    public final List<String> homeTowns = new ArrayList<>();
    public final List<String> salutations = new ArrayList<>();
    public final Map<String,String> genders = new HashMap<>();

    public UserService(HibernateConfig hibernateConfig){
        this.hibernateConfig = hibernateConfig;
        userList = new ArrayList<>();
        homeTowns.add("Dhaka");
        homeTowns.add("Comilla");
        homeTowns.add("Chittagong");
        homeTowns.add("Noakhali");
        homeTowns.add("Gramer Bari");
        salutations.add("Mr.");
        salutations.add("Mrs.");
        salutations.add("Md.");
        salutations.add("Dr.");
        genders.put("M","Male");
        genders.put("F","Female");
        genders.put("O","Others");
    }

    public List<String> getHomeTowns(){
        return this.homeTowns;
    }

    public List<String> getSalutations(){
        return this.salutations;
    }

    public Map<String,String> getGenders(){
        return this.genders;
    }


    public List<User> allUsers(){
/*
        Session session = hibernateConfig.getSession();
        String query = "select User from User";

        Query query1 = session.createQuery(query);
        var userList = (List<User>) query1.list();

        return userList; */
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(User.class);
        var root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);

        return hibernateConfig.getSession()
                .getEntityManagerFactory()
                .createEntityManager()
                .createQuery(criteriaQuery)
                .getResultList();
    }
    public User getUserById(long id){
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(User.class);
        var root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),id));

        return hibernateConfig.getSession()
                .getEntityManagerFactory()
                .createEntityManager()
                .createQuery(criteriaQuery)
                .getSingleResult();
    }


    public void addUser(User userDto) {
        System.out.println("save method of userService------------------------------------------------------");
        var session = hibernateConfig.getSession();
        Transaction tx = session.getTransaction();
        if(!tx.isActive()){
            tx = session.beginTransaction();
        }
        var userEntity = new User();
        BeanUtils.copyProperties(userDto,userEntity);
        session.save(userEntity);
        session.flush();
        tx.commit();
        System.out.println("---------------------------------------------------");
        System.out.println("User is saved");
        System.out.println(userEntity);
    }

//    public void deleteUser(User user){
//        delete(user);
//    }

}
