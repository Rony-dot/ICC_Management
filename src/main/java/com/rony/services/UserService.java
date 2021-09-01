package com.rony.services;

import com.rony.config.HibernateConfig;
import com.rony.models.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
//    public final List<String> homeTowns = new ArrayList<>();
//    public final List<String> salutations = new ArrayList<>();
//    public final Map<String,String> genders = new HashMap<>();

//    public UserService() {
//        super(new User());
//        userList = new ArrayList<>();
//        homeTowns.add("Dhaka");
//        homeTowns.add("Comilla");
//        homeTowns.add("Chittagong");
//        homeTowns.add("Noakhali");
//        homeTowns.add("Gramer Bari");
//        salutations.add("Mr.");
//        salutations.add("Mrs.");
//        salutations.add("Md.");
//        salutations.add("Dr.");
//        genders.put("M","Male");
//        genders.put("F","Female");
//        genders.put("O","Others");
//    }

    public List<User> allUsers(){
//        List<User> userList = getAll();
        Session session = hibernateConfig.getSession();
        String query = "select User from User";

        Query query1 = session.createQuery(query);
        var userList = (List<User>) query1.list();

        return userList;
    }

    public void addUser(User user) {
        this.userList.add(user);
//        save(user);
    }

//    public void deleteUser(User user){
//        delete(user);
//    }

}
