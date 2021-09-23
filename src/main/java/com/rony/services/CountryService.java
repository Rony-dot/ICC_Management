package com.rony.services;

import com.rony.config.HibernateConfig;

import com.rony.models.Player;

import com.rony.requestDto.CountryReqDto;
import com.rony.models.Country;
import com.rony.responseDto.CountryRespDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public List<CountryRespDto> allCountries(){
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Country.class);
        var root = criteriaQuery.from(Country.class);
        criteriaQuery.select(root);

        var resultList =  hibernateConfig.query(criteriaQuery).getResultList();

        return resultList.size() > 0 ? convertToCountryRespDto(resultList) : null;

    }

    private List<CountryRespDto> convertToCountryRespDto(List<Country> resultList) {
        List<CountryRespDto> countryRespDtoList = new ArrayList<>();
        for(Country country : resultList){
            CountryRespDto countryRespDto = new CountryRespDto();
            BeanUtils.copyProperties(country,countryRespDto);
            countryRespDto.setId(String.valueOf(country.getId()));
            countryRespDto.setName(country.getName());
            countryRespDto.setCountryManagerName(country.getManagingDirector().getName());
            countryRespDto.setCountryManagerEmail(country.getManagingDirector().getEmail());
            countryRespDto.setPlayerNames(country.getPlayerList().stream().map(p -> p.getUserInfo().getName()).collect(Collectors.toList()));
            countryRespDtoList.add(countryRespDto);
        }
        return countryRespDtoList;
    }

    public String getCountryByCMId(long id) {
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Country.class);
        var root = criteriaQuery.from(Country.class);
        criteriaQuery.select(root);

        var resultList =  hibernateConfig.query(criteriaQuery).getResultList();
        if(resultList.size() > 0){
           var country = resultList.stream().filter( c -> c.getManagingDirector().getId() == id).findFirst().orElse(null);
           return country != null ? country.getId()+"" : null;
        }
        return null;
//        var countries = allCountries();
//        var userEmail = userService.getUserById(id).getEmail();
//        CountryRespDto countryRespDto = countries.stream().filter(c -> c.getCountryManagerEmail().equals(userEmail)).findFirst().orElse(null);
//
//        return countryRespDto!=null? countryRespDto.getId() : null;
    }


  /*  public void saveCountry(CountryReqDto countryDto, User userDto){
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

    public void deleteCountry(CountryReqDto country){

    }

    public com.rony.models.Country getCountryById(String id) {
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(com.rony.models.Country.class);
        var root = criteriaQuery.from(com.rony.models.Country.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),Long.parseLong(id)));

        return hibernateConfig.query(criteriaQuery).getSingleResult();
    }

//
//    public void saveCountry(CountryReqDto countryDto, long idMD, long[] playerIds) {
//        System.out.println("save method of country service------------------------------------------------------");
//
//        var countryEntity = new CountryReqDto();
//        BeanUtils.copyProperties(countryDto,countryEntity);
//
//        var managingDirectorDto = userService.getUserById(idMD);
//        managingDirectorDto.setUserRole(roleService.findByRoleName("ROLE_TEAM_MANAGER"));
//        countryEntity.setManagingDirector(managingDirectorDto);
//        List<Player> playerList = new ArrayList<>();
//        countryEntity.setPlayerList(playerList);
//
//        hibernateConfig.saveObject(countryEntity);
//
//        System.out.println("---------------------------------------------------");
//        System.out.println("country is saved");
//        System.out.println(countryEntity);
//    }


    public void saveCountry(CountryReqDto countryReqDto) {
        System.out.println("save method of country service------------------------------------------------------");

        // why full qualified class name ?
        var countryEntity = new com.rony.models.Country();
        BeanUtils.copyProperties(countryReqDto,countryEntity);

        var managingDirectorDto = userService.getUserById(countryReqDto.getCountryManagerId());
        managingDirectorDto.setUserRole(roleService.findByRoleName("ROLE_TEAM_MANAGER"));
        countryEntity.setManagingDirector(managingDirectorDto);
        List<Player> playerList = new ArrayList<>();
        countryEntity.setPlayerList(playerList);

        hibernateConfig.saveObject(countryEntity);

        System.out.println("---------------------------------------------------");
        System.out.println("country entity is saved");
        System.out.println(countryEntity);
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

 */
}
