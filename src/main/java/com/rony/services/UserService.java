package com.rony.services;

import com.rony.config.HibernateConfig;
import com.rony.enums.Countries;
import com.rony.enums.Genders;
import com.rony.enums.HomeTowns;
import com.rony.enums.Salutations;
import com.rony.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private HibernateConfig hibernateConfig;
    @Autowired
    private PasswordEncoder passwordEncoder;


    private List<User> userList;
    public final List<String> homeTowns = new ArrayList<>();
    public final List<String> salutations = new ArrayList<>();
    private final List<String> countries = new ArrayList<>();
    public final Map<String,String> genders = new HashMap<>();

    public UserService(HibernateConfig hibernateConfig){
        this.hibernateConfig = hibernateConfig;
        userList = new ArrayList<>();
        homeTowns.addAll(Stream.of(HomeTowns.values())
                .map(HomeTowns::name)
                .collect(Collectors.toList()));
        salutations.addAll(Stream.of(Salutations.values())
                .map(Salutations::name)
                .collect(Collectors.toList()));
        countries.addAll(Stream.of(Countries.values())
                .map(Countries::name)
                .collect(Collectors.toList()));
        genders.put("M","Male");
        genders.put("F","Female");
        genders.put("O","Others");
    }

    public List<String> getHomeTowns(){
        return this.homeTowns;
    }

    public  List<String> getCountries(){
        return this.countries;
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

    public User getUserById(String id){
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(User.class);
        var root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),Long.parseLong(id)));

        return hibernateConfig.query(criteriaQuery).getSingleResult();
//        return hibernateConfig.getSession()
//                .getEntityManagerFactory()
//                .createEntityManager()
//                .createQuery(criteriaQuery)
//                .getSingleResult();
    }

    public User getUserByEmail (String email) throws NoResultException {
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(User.class);
        var root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("email"),email));

        return hibernateConfig.getSession()
                .getEntityManagerFactory()
                .createEntityManager()
                .createQuery(criteriaQuery)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
    }

    public void addUser(User userDto) {

//        var session = hibernateConfig.getSession();
//        Transaction tx = session.getTransaction();
//        if(!tx.isActive()){
//            tx = session.beginTransaction();
//        }
        var userEntity = new User();
        BeanUtils.copyProperties(userDto,userEntity);
        // encoding password
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));
        System.err.println("save method of userService------------------------------------------------------");
        System.err.println(userEntity);
        System.err.println("---------------------------------------------------");
        hibernateConfig.saveObject(userEntity);
//        session.save(userEntity);
//        session.flush();
//        tx.commit();

        System.out.println("User is saved");
        System.out.println(userEntity);
    }

    public boolean validateUser(String email, String password) {
        try{
            if(getUserByEmail(email).getPassword().equals(password)){
                return true;
            }else {
                System.out.println("user not exist");
                return false;
            }
        }catch (NoResultException e){
            System.err.println("exception of validate user in userService");
           return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // or can directly call " getUserByEmail(email) ", bcz it is allReady defined previously
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(User.class);
        var root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("email"),email));

        var result =  hibernateConfig.getSession()
                .getEntityManagerFactory()
                .createEntityManager()
                .createQuery(criteriaQuery)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);
        return result;
    }

//    public void deleteUser(User user){
//        delete(user);
//    }

}
