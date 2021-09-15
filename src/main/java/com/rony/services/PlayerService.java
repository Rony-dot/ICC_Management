package com.rony.services;

import com.rony.config.HibernateConfig;
import com.rony.models.Player;
import com.rony.models.User;
import lombok.extern.flogger.Flogger;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private HibernateConfig hibernateConfig;
    @Autowired
    private UserService userService;
    @Autowired
    private CountryService countryService;

    public PlayerService(HibernateConfig hibernateConfig, UserService userService) {
        this.hibernateConfig = hibernateConfig;
        this.userService = userService;
    }

    public List<Player> allPlayers(){
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var  criteriaQuery = criteriaBuilder.createQuery(Player.class);
        var root = criteriaQuery.from(Player.class);
        criteriaQuery.select(root);

        return hibernateConfig.query(criteriaQuery).getResultList();

    }

    public Player getPlayerByUserId(long id){
        // partial done
//        var user = userService.getUserById(id);
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Player.class);
        var root = criteriaQuery.from(Player.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("userInfo"),id));

        return hibernateConfig.query(criteriaQuery).getResultList().stream()
                .findFirst().orElse(null);

    }

    public Player getPlayerById(long id){
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Player.class);
        var root = criteriaQuery.from(Player.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),id));

       return hibernateConfig.query(criteriaQuery)
               .getResultList().stream()
               .findFirst().orElse(null);

    }

    public void addPlayer(Player playerDto, long userId, long cid){
        System.err.println("save method of player service-----------------------------------");

        if(getPlayerByUserId(userId)==null){
            var playerEntity = new Player();
            BeanUtils.copyProperties(playerDto,playerEntity);
            var userInfo = userService.getUserById(userId);
            playerEntity.setUserInfo(userInfo);
            playerEntity.setId(userId);

            hibernateConfig.saveObject(playerEntity);

            var countryEntity = countryService.getCountryById(cid);
            var player = getPlayerByUserId(userId);
            countryEntity.getPlayerList().add(player);
//            playerList.add(player);
//            countryEntity.setPlayerList(playerList);

            hibernateConfig.saveObject(countryEntity);
            System.out.println("assign player to country success!");

            System.err.println("---------------------------------------------------");
            System.err.println("player along with country  is saved");
            System.err.println(playerEntity);
        }else {
            System.out.println("-----======================-----------------");
            System.out.println("player exists !!! ");
        }

    }

    /*public void addPlayer(Player playerDto, long idTeam){
        System.err.println("save method of player service------------------------------------------------------");
        var session = hibernateConfig.getSession();
        Transaction tx = session.getTransaction();
        if(!tx.isActive()){
            tx = session.beginTransaction();
        }
        var playerEntity = getPlayerById(idTeam);
        BeanUtils.copyProperties(playerDto,playerEntity);
        session.save(playerEntity);
        session.flush();
        tx.commit();
        System.err.println("---------------------------------------------------");
        System.err.println("player  is saved");
        System.err.println(playerEntity);
    }

     */
}
