package com.rony.services;

import com.rony.config.HibernateConfig;
import com.rony.models.Country;
import com.rony.models.Player;
import com.rony.models.Team;
import com.rony.models.User;
import com.rony.requestDto.TeamReqDto;
import com.rony.responseDto.TeamRespDto;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TeamService {
    @Autowired
    private HibernateConfig hibernateConfig;
    @Autowired
    private UserService userService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private RoleService roleService;

    public TeamService(HibernateConfig hibernateConfig, UserService userService, PlayerService playerService, CountryService countryService) {
        this.hibernateConfig = hibernateConfig;
        this.userService = userService;
        this.playerService = playerService;
        this.countryService = countryService;
    }

    public List<TeamRespDto> allTeams(){
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Team.class);
        var root = criteriaQuery.from(Team.class);
        criteriaQuery.select(root);

        var resultList =  hibernateConfig.query(criteriaQuery).getResultList();
        return resultList.size() > 0 ? convertToTeamRespDto(resultList) : null;
    }

    private List<TeamRespDto> convertToTeamRespDto(List<Team> resultList) {
        List<TeamRespDto> teamRespDtoList = new ArrayList<>();
        for(Team team : resultList){
            TeamRespDto teamRespDto = new TeamRespDto();
            BeanUtils.copyProperties(team,teamRespDto);
            teamRespDto.setId(String.valueOf(team.getId()));
            teamRespDto.setCoachName(team.getCoach().getName());
            teamRespDto.setCountryName(team.getCountry().getName());
            teamRespDto.setPlayerNameList(team.getPlayerList().stream()
                    .map(Player::getUserInfo)
                    .map(User :: getName)
                    .collect(Collectors.toList()));
            teamRespDtoList.add(teamRespDto);
        }
        return teamRespDtoList;
    }

  /*  public void saveCountry(Team teamDto, User userDto){
        System.out.println("save method of country service------------------------------------------------------");
        var session = hibernateConfig.getSession();
        Transaction tx = session.getTransaction();
        if(!tx.isActive()){
            tx = session.beginTransaction();
        }
        var countryEntity = new CountryReqDto();
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

    public Team getTeamById(String id) {
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Team.class);
        var root = criteriaQuery.from(Team.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),Long.parseLong(id)));

        var result =  hibernateConfig.query(criteriaQuery).getSingleResult();
        return result;
//        return result!=null ? convertToTeamRespDto(result) : null;

        // TODO need to check below works or not, => can handle null
//        return Stream.of(hibernateConfig.query(criteriaQuery).getSingleResult()).findFirst().orElse(null);
    }
    private TeamRespDto convertToTeamRespDto(Team team) {
        TeamRespDto teamRespDto = new TeamRespDto();
        BeanUtils.copyProperties(team,teamRespDto);
        teamRespDto.setCoachName(team.getCoach().getName());
        teamRespDto.setCountryName(team.getCountry().getName());
        teamRespDto.setPlayerNameList(team.getPlayerList().stream()
                .map(Player::getUserInfo)
                .map(User :: getName)
                .collect(Collectors.toList()));

        return teamRespDto;
    }

    public void saveTeam(TeamReqDto teamReqDto, String cid) {
        System.err.println("save method of Team service------------------------------------------------------");

        var coachDto = userService.getUserById(teamReqDto.getCoachId());
        coachDto.setUserRole(roleService.findByRoleName("ROLE_COACH"));
        hibernateConfig.updateObject(coachDto);

        var teamEntity = new Team();
        BeanUtils.copyProperties(teamReqDto,teamEntity);

        teamEntity.setCoach(coachDto);
        List<Player> playerList = new ArrayList<>();
        for(String id: teamReqDto.getPlayerIdList()){
            playerList.add(playerService.getPlayerById(id));
        }
        teamEntity.setPlayerList(playerList);
        var country = countryService.getCountryById(cid);
        teamEntity.setCountry(country);

        hibernateConfig.saveObject(teamEntity);

        System.err.println("---------------------------------------------------");
        System.err.println("team is saved");
        System.err.println(teamEntity);
    }

  /*
    public void updateCountry(CountryReqDto countryDto, long id, long idMD, long[] playerIds) {
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
        List<User> playerList = new ArrayList<>();
        for(long pId: playerIds){
            playerList.add(userService.getUserById(pId));
        }
        countryEntity.setPlayerList(playerList);

        session.update(countryEntity);
        session.flush();
        tx.commit();
        System.err.println("---------------------------------------------------");
        System.err.println("country is updated");
        System.err.println(countryEntity);
    }

    */

}
