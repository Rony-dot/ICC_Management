package com.rony.services;

import com.rony.config.HibernateConfig;
import com.rony.models.Event;
import com.rony.models.Series;
import com.rony.models.Team;
import com.rony.models.User;
import com.rony.requestDto.SeriesReqDto;
import com.rony.responseDto.SeriesRespDto;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<SeriesRespDto> allSeries(){
        var cb = hibernateConfig.getCriteriaBuilder();
        var cq = cb.createQuery(Series.class);
        var root = cq.from(Series.class);
        cq.select(root);

        var resultList = hibernateConfig.query(cq).getResultList();
        return resultList.size() > 0 ? convertToSeriesRespDtos(resultList) : null;
    }

    private List<SeriesRespDto> convertToSeriesRespDtos(List<Series> resultList) {
        List<SeriesRespDto> seriesRespDtoList = new ArrayList<>();
        for(Series series : resultList){
            SeriesRespDto seriesRespDto = new SeriesRespDto();
            BeanUtils.copyProperties(series,seriesRespDto);
            seriesRespDto.setId(String.valueOf(series.getId()));
            seriesRespDto.setEventNameList(series.getEventList().stream()
                    .map(Event::getName)
                    .collect(Collectors.toList()));
            seriesRespDto.setParticipantTeamNames(series.getParticipantTeams().stream()
                    .map(Team::getName)
                    .collect(Collectors.toList()));
            seriesRespDtoList.add(seriesRespDto);
        }
        return seriesRespDtoList;
    }

    public void saveSeries(SeriesReqDto seriesReqDto) {
        System.err.println("save method of Series service------------------------------------------------------");

        var seriesEntity = new Series();
        BeanUtils.copyProperties(seriesReqDto,seriesEntity);

        List<Event> eventList = new ArrayList<>();
        for(String id: seriesReqDto.getEventIdList()){
            eventList.add(eventService.getEventById(id));
        }
        seriesEntity.setEventList(eventList);

        List<Team> teamList = new ArrayList<>();
        for(String id: seriesReqDto.getParticipantTeamIds()){
            teamList.add(teamService.getTeamById(id));
        }
        seriesEntity.setParticipantTeams(teamList);
        var session = hibernateConfig.getSession();
        Transaction tx = session.getTransaction();
        if(!tx.isActive()){
            tx = session.beginTransaction();
        }
        session.save(seriesEntity);
        session.flush();
        tx.commit();
        System.err.println("---------------------------------------------------");
        System.err.println("Series is saved");
//        System.err.println(seriesEntity);
    }
}
