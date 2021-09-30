package com.rony.services;

import com.rony.config.HibernateConfig;
import com.rony.models.Country;
import com.rony.models.Player;
import com.rony.models.User;
import com.rony.requestDto.PlayerReqDto;
import com.rony.responseDto.PlayerRespDto;
import lombok.extern.flogger.Flogger;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    Logger logger = LogManager.getLogger(PlayerService.class);

    public PlayerService(HibernateConfig hibernateConfig, UserService userService) {
        this.hibernateConfig = hibernateConfig;
        this.userService = userService;
    }

    public List<Player> allPlayers(){
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var  criteriaQuery = criteriaBuilder.createQuery(Player.class);
        var root = criteriaQuery.from(Player.class);
        criteriaQuery.select(root);

        var resultList =  hibernateConfig.query(criteriaQuery).getResultList();

        return  resultList.size() > 0 ? resultList : null;

    }

    public List<PlayerRespDto> playerRespDtos() {
        var resultList = allPlayers();
        List<PlayerRespDto> playerRespDtoList = new ArrayList<>();
        if(resultList != null){
            for(Player player : resultList){
                PlayerRespDto playerRespDto = new PlayerRespDto();
                BeanUtils.copyProperties(player,playerRespDto);
                playerRespDto.setId(String.valueOf(player.getId()));
                playerRespDto.setUserName(player.getUserInfo().getName());
                playerRespDtoList.add(playerRespDto);
            }
        }
        return playerRespDtoList;
    }


    public Player getPlayerByUserId(String id){
        // partial done
//        var user = userService.getUserById(id);
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Player.class);
        var root = criteriaQuery.from(Player.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("userInfo"),Long.parseLong(id)));

        return hibernateConfig.query(criteriaQuery).getResultList().stream()
                .findFirst().orElse(null);

    }

    public Player getPlayerById(String id){
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Player.class);
        var root = criteriaQuery.from(Player.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),Long.parseLong(id)));

       return hibernateConfig.query(criteriaQuery)
               .getResultList().stream()
               .findFirst().orElse(null);

    }

    public void addPlayer(PlayerReqDto playerReqDto, String cid){
        System.err.println("save method of player service-----------------------------------");

        // checking if any player exists with this user_id => user info
        if(getPlayerByUserId(playerReqDto.getUserId())==null){
            var playerEntity = new Player();
            BeanUtils.copyProperties(playerReqDto,playerEntity);
            var userInfo = userService.getUserById(playerReqDto.getUserId());
            playerEntity.setUserInfo(userInfo);
            playerEntity.setId(Long.parseLong(playerReqDto.getUserId()));

            hibernateConfig.saveObject(playerEntity);

            var player = getPlayerByUserId(playerReqDto.getUserId());

            logger.info("country id : "+ cid);

            Session session = hibernateConfig.getSession();
            var tx = session.getTransaction();
            if (!tx.isActive()) {
                tx = session.beginTransaction();
            }
            var countryEntity = session.get(Country.class, Long.parseLong(cid));
            countryEntity.getPlayerList().add(player);
            // omly commit will make the changes save to DB, because it is in the same session
            tx.commit();
//            playerList.add(player);
//            countryEntity.setPlayerList(playerList);

//            hibernateConfig.saveObject(countryEntity);

        }else {
            logger.error("player exists !!! ");
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
