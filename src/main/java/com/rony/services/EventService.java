package com.rony.services;

import com.rony.config.HibernateConfig;
import com.rony.models.Country;
import com.rony.models.Event;
import com.rony.models.User;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private HibernateConfig hibernateConfig;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;

    public EventService(HibernateConfig hibernateConfig, UserService userService, TeamService teamService) {
        this.hibernateConfig = hibernateConfig;
        this.userService = userService;
        this.teamService = teamService;
    }

    public List<Event> allEvents(){
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Event.class);
        var root = criteriaQuery.from(Event.class);
        criteriaQuery.select(root);

        return hibernateConfig.getSession()
                .getEntityManagerFactory()
                .createEntityManager()
                .createQuery(criteriaQuery)
                .getResultList();
    }

    public Event getEventById(long id) {
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Event.class);
        var root = criteriaQuery.from(Event.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),id));

        return hibernateConfig.getSession()
                .getEntityManagerFactory()
                .createEntityManager()
                .createQuery(criteriaQuery)
                .getSingleResult();
    }

    public void saveEvent(Event eventDto, long idTeam1, long idTeam2, long[] umpireIds) {
        System.err.println("save method of Event service------------------------------------------------------");
        var session = hibernateConfig.getSession();
        Transaction tx = session.getTransaction();
        if(!tx.isActive()){
            tx = session.beginTransaction();
        }
        var eventEntity = new Event();
        BeanUtils.copyProperties(eventDto,eventEntity);
        var team1 = teamService.getTeamById(idTeam1);
        var team2 = teamService.getTeamById(idTeam2);
        eventEntity.setTeam1(team1);
        eventEntity.setTeam2(team2);
        List<User> umpireList = new ArrayList<>();
        for(long id: umpireIds){
            umpireList.add(userService.getUserById(id));
        }
        eventEntity.setUmpires(umpireList);
        session.save(eventEntity);
        session.flush();
        tx.commit();
        System.err.println("---------------------------------------------------");
        System.err.println("Event is saved");
        System.err.println(eventEntity);
    }
}
