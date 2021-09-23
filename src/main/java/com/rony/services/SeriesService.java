package com.rony.services;

import com.rony.config.HibernateConfig;
import com.rony.models.Event;
import com.rony.models.Series;
import com.rony.models.Team;
import com.rony.models.User;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SeriesService {
    @Autowired
    private HibernateConfig hibernateConfig;
    @Autowired
    private EventService eventService;
    @Autowired
    private TeamService teamService;

    public SeriesService(HibernateConfig hibernateConfig, EventService eventService, TeamService teamService) {
        this.hibernateConfig = hibernateConfig;
        this.eventService = eventService;
        this.teamService = teamService;
    }

    public List<Series> allSeries(){
        var cb = hibernateConfig.getCriteriaBuilder();
        var cq = cb.createQuery(Series.class);
        var root = cq.from(Series.class);
        cq.select(root);

        return hibernateConfig.getSession()
                .getEntityManagerFactory()
                .createEntityManager()
                .createQuery(cq)
                .getResultList();
    }

    public void saveSeries(Series seriesDto, String[] eventIds, String[] teamIds) {
        System.err.println("save method of Series service------------------------------------------------------");
        var session = hibernateConfig.getSession();
        Transaction tx = session.getTransaction();
        if(!tx.isActive()){
            tx = session.beginTransaction();
        }
        var seriesEntity = new Series();
        BeanUtils.copyProperties(seriesDto,seriesEntity);

        List<Event> eventList = new ArrayList<>();
        for(String id: eventIds){
            eventList.add(eventService.getEventById(id));
        }
        seriesEntity.setEventList(eventList);

        List<Team> teamList = new ArrayList<>();
        for(String id: teamIds){
            teamList.add(teamService.getTeamById(id));
        }
        seriesEntity.setParticipantTeams(teamList);

        session.save(seriesEntity);
        session.flush();
        tx.commit();
        System.err.println("---------------------------------------------------");
        System.err.println("Series is saved");
        System.err.println(seriesEntity);

    }
}
