package com.rony.services;

import com.rony.config.HibernateConfig;
import com.rony.models.Country;
import com.rony.models.Event;
import com.rony.models.User;
import com.rony.requestDto.EventReqDto;
import com.rony.responseDto.EventRespDto;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.annotations.AttributeAccessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private HibernateConfig hibernateConfig;
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private RoleService roleService;

    public EventService(HibernateConfig hibernateConfig, UserService userService, TeamService teamService) {
        this.hibernateConfig = hibernateConfig;
        this.userService = userService;
        this.teamService = teamService;
    }

    public List<EventRespDto> allEvents(){
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Event.class);
        var root = criteriaQuery.from(Event.class);
        criteriaQuery.select(root);

        var resultList =  hibernateConfig.query(criteriaQuery).getResultList();

        return  resultList.size() > 0 ? convertToEventRespDtos(resultList) : null;
    }

    private List<EventRespDto> convertToEventRespDtos(List<Event> resultList) {
        List<EventRespDto> eventRespDtoList = new ArrayList<>();
        for(Event event : resultList){
            EventRespDto eventRespDto = new EventRespDto();
            BeanUtils.copyProperties(event,eventRespDto);
            eventRespDto.setId(String.valueOf(event.getId()));
            eventRespDto.setTeam1Name(event.getTeam1().getName());
            eventRespDto.setTeam1CaptainName(event.getTeam1Captain().getUserInfo().getName());
            eventRespDto.setTeam2Name(event.getTeam2().getName());
            eventRespDto.setTeam2CaptainName(event.getTeam2Captain().getUserInfo().getName());
            eventRespDto.setUmpireNames(event.getUmpires().stream()
                    .map(User::getName)
                    .collect(Collectors.toList()));
            eventRespDtoList.add(eventRespDto);
        }
        return eventRespDtoList;
    }

    public Event getEventById(String id) {
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

    public void saveEvent(EventReqDto eventReqDto, String[] newUmpireIds) {

        System.err.println("save method of Event service------------------------------------------------------");

        // update users as new umpire by updating role
        if( newUmpireIds!=null){
            for(String id: newUmpireIds){
                User umpire = userService.getUserById(id);
                umpire.setUserRole(roleService.findByRoleName("ROLE_UMPIRE"));
                hibernateConfig.updateObject(umpire);
            }
        }

        // fetch updated umpires
        List<User> newUmpires = new ArrayList<>();
        if(newUmpireIds!=null) {
            for (String id : newUmpireIds) {
                User umpire = userService.getUserById(id);
                newUmpires.add(umpire);
            }
        }

        var eventEntity = new Event();
        BeanUtils.copyProperties(eventReqDto,eventEntity);
        var team1 = teamService.getTeamById(eventReqDto.getIdTeam1());
        var team1Cap = playerService.getPlayerById(eventReqDto.getIdTeam1Cap());
        var team2 = teamService.getTeamById(eventReqDto.getIdTeam2());
        var team2Cap = playerService.getPlayerById(eventReqDto.getIdTeam2Cap());
        eventEntity.setTeam1(team1);
        eventEntity.setTeam1Captain(team1Cap);
        eventEntity.setTeam2(team2);
        eventEntity.setTeam2Captain(team2Cap);
        List<User> umpireList = new ArrayList<>();

        if( eventReqDto.getUmpireIds() != null ){
            for(String id : eventReqDto.getUmpireIds()){
                umpireList.add(userService.getUserById(id));
            }
        }
        if(newUmpires !=null ){
            for(User umpire : newUmpires){
                umpireList.add(umpire);
            }
        }
        eventEntity.setUmpires(umpireList);
        hibernateConfig.saveObject(eventEntity);

        System.err.println("---------------------------------------------------");
        System.err.println("Event is saved");
//        System.err.println(eventEntity);
    }
}
