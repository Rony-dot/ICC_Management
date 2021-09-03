package com.rony.services;

import com.rony.config.HibernateConfig;
import com.rony.models.Player;
import com.rony.models.User;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private HibernateConfig hibernateConfig;

    public PlayerService(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

    public List<Player> allPlayers(){
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var  criteriaQuery = criteriaBuilder.createQuery(Player.class);
        var root = criteriaQuery.from(Player.class);
        criteriaQuery.select(root);

        var result = hibernateConfig.getSession()
                .getEntityManagerFactory()
                .createEntityManager()
                .createQuery(criteriaQuery)
                .getResultList();
        return result;
    }

    public Player getPlayerById(long id){
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Player.class);
        var root = criteriaQuery.from(Player.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),id));

        return hibernateConfig.getSession()
                .getEntityManagerFactory()
                .createEntityManager()
                .createQuery(criteriaQuery)
                .getSingleResult();
    }

    public void addPlayer(Player playerDto, long idTeam){
        System.out.println("save method of player service------------------------------------------------------");
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
        System.out.println("---------------------------------------------------");
        System.out.println("player  is saved");
        System.out.println(playerEntity);
    }
}
