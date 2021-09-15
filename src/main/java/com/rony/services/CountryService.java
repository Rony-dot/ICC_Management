package com.rony.services;

import com.rony.config.HibernateConfig;
import com.rony.models.Country;
import com.rony.models.Player;
import com.rony.models.User;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {
    @Autowired
    private HibernateConfig hibernateConfig;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    public CountryService(HibernateConfig hibernateConfig, UserService userService) {
        this.hibernateConfig = hibernateConfig;
        this.userService = userService;
    }

    public List<Country> allCountries(){
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Country.class);
        var root = criteriaQuery.from(Country.class);
        criteriaQuery.select(root);

        var resultList =  hibernateConfig.query(criteriaQuery).getResultList();
        return resultList.size() > 0  ? resultList : null;
    }

  /*  public void saveCountry(Country countryDto, User userDto){
        System.out.println("save method of country service------------------------------------------------------");
        var session = hibernateConfig.getSession();
        Transaction tx = session.getTransaction();
        if(!tx.isActive()){
            tx = session.beginTransaction();
        }
        var countryEntity = new Country();
        BeanUtils.copyProperties(countryDto,countryEntity);
        countryEntity.setManagingDirector(userDto);
        session.save(countryEntity);
        session.flush();
        tx.commit();
        System.out.println("---------------------------------------------------");
        System.out.println("country is saved");
        System.out.println(countryEntity);
    } */

    public void deleteCountry(Country country){

    }

    public Country getCountryById(long id) {
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Country.class);
        var root = criteriaQuery.from(Country.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),id));

        return hibernateConfig.query(criteriaQuery).getSingleResult();
    }

    public void saveCountry(Country countryDto, long idMD, long[] playerIds) {
        System.out.println("save method of country service------------------------------------------------------");

        var countryEntity = new Country();
        BeanUtils.copyProperties(countryDto,countryEntity);

        var managingDirectorDto = userService.getUserById(idMD);
        managingDirectorDto.setUserRole(roleService.findByRoleName("ROLE_TEAM_MANAGER"));
        countryEntity.setManagingDirector(managingDirectorDto);
        List<Player> playerList = new ArrayList<>();
        countryEntity.setPlayerList(playerList);

        hibernateConfig.saveObject(countryEntity);

        System.out.println("---------------------------------------------------");
        System.out.println("country is saved");
        System.out.println(countryEntity);
    }


    public void updateCountry(Country countryDto, long id, long idMD, long[] playerIds) {
        System.err.println("update method of country service--------------------------------------------");
        var session = hibernateConfig.getSession();
        Transaction tx = session.getTransaction();
        if(!tx.isActive()){
            tx = session.beginTransaction();
        }
        var countryEntity = getCountryById(id);
        BeanUtils.copyProperties(countryDto,countryEntity);
        var managingDirectorDto = userService.getUserById(idMD);
        countryEntity.setManagingDirector(managingDirectorDto);
//        List<Player> playerList = new ArrayList<>();
//        for(long pId: playerIds){
//            playerList.add(userService.getUserById(pId));
//        }
//        countryEntity.setPlayerList(playerList);

        session.update(countryEntity);
        session.flush();
        tx.commit();
        System.err.println("---------------------------------------------------");
        System.err.println("country is updated");
        System.err.println(countryEntity);
    }
}
