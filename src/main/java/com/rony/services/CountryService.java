package com.rony.services;

import com.rony.config.HibernateConfig;

import com.rony.exceptions.ResourceAlreadyExistsException;
import com.rony.exceptions.ResourceNotFoundException;
import com.rony.models.Player;

import com.rony.models.User;
import com.rony.requestDto.CountryReqDto;
import com.rony.models.Country;
import com.rony.responseDto.CountryRespDto;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
    @Autowired
    private  PlayerService playerService;

    Logger logger = LogManager.getLogger(CountryService.class);

    public CountryService(HibernateConfig hibernateConfig, UserService userService) {
        this.hibernateConfig = hibernateConfig;
        this.userService = userService;
    }

    public List<Country> allCountries(){
        CriteriaBuilder criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root root = criteriaQuery.from(Country.class);
        criteriaQuery.select(root);

        List<Country> resultList =  hibernateConfig.query(criteriaQuery).getResultList();

        return resultList.size() > 0 ? resultList : null;
    }

    public List<CountryRespDto> CountryRespDtoList() {
        List<Country> resultList = allCountries();
        List<CountryRespDto> countryRespDtoList = new ArrayList<>();
        if(!resultList.isEmpty()){
            for(Country country : resultList){
                CountryRespDto countryRespDto = new CountryRespDto();
                BeanUtils.copyProperties(country,countryRespDto);
                // we explicitly setting country id because it can't copy id by BeanUtils.copyProperties()
                // becase we used id inside country entity is long but,
                // the country id inside country response entity is of type string
                countryRespDto.setId(String.valueOf(country.getId()));
                countryRespDto.setName(country.getName());
                if(country.getManagingDirector() != null){
                    countryRespDto.setCountryManagerName(country.getManagingDirector().getName());
                    countryRespDto.setCountryManagerEmail(country.getManagingDirector().getEmail()+"");
                }
                if(country.getPlayerList().size() > 0 ){
                    countryRespDto.setPlayerNames(country.getPlayerList()
                            .stream().map(p -> p.getUserInfo().getName())
                            .collect(Collectors.toList()));
                }
                countryRespDtoList.add(countryRespDto);
            }
        }
        return countryRespDtoList;
    }

    public String getCountryByCMId(String id) {
        CriteriaBuilder criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Country.class);
        Root root = criteriaQuery.from(Country.class);
        criteriaQuery.select(root);

        List<Country> resultList =  hibernateConfig.query(criteriaQuery).getResultList();
        if(resultList.size() > 0){
           Country country = resultList.stream()
                   .filter( c -> c.getManagingDirector().getId() == Long.parseLong(id))
                   .findFirst()
                   .orElse(null);
           return country != null ? country.getId()+"" : null;
        }
        return null;
//        var countries = allCountries();
//        var userEmail = userService.getUserById(id).getEmail();
//        CountryRespDto countryRespDto = countries.stream()
//                .filter(c -> c.getCountryManagerEmail().equals(userEmail))
//                .findFirst()
//                .orElse(null);
//
//        return countryRespDto != null ? countryRespDto.getId() : null;
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
        CriteriaBuilder criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        CriteriaQuery<Country> criteriaQuery = criteriaBuilder.createQuery(com.rony.models.Country.class);
        Root root = criteriaQuery.from(com.rony.models.Country.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("id"),Long.parseLong(id)));

        return hibernateConfig.query(criteriaQuery).getSingleResult();
    }

    public void checkCountryInList(Country c) {
        if (allCountries().stream()
                .anyMatch(country -> country.getCountryCode().equals(c.getCountryCode()))) {
            throw new ResourceAlreadyExistsException("Country already exists in list");
        }
    }

    public Country getCountryByCode(String countryCode) {
        // **************************** HQL Start ******************************//
//		var session = hibernateConfig.getSession();
//		var transaction = session.beginTransaction();
//		var query = session
//				.getEntityManagerFactory()
//				.createEntityManager()
//				.createQuery("SELECT c from com.spring5.practice.model.Country c where c.countryCode=:countryCode", Country.class);
//		query.setParameter("countryCode", countryCode);
        // **************************** HQL End ******************************//

        // **************************** Criteria Query Start
        // **************************//
        CriteriaBuilder cb = hibernateConfig.getCriteriaBuilder();
        CriteriaQuery<Country> cq = cb.createQuery(Country.class);
        Root<Country> root = cq.from(Country.class);
        cq.where(cb.equal(root.get("countryCode"), countryCode));
        List<Country> result = hibernateConfig.query(cq).getResultList();
        // **************************** Criteria Query End **************************//
        return Optional.ofNullable(result.get(0))
                .orElseThrow(() -> new ResourceNotFoundException("Country not found with this code"));
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


    public void updateCountry(CountryReqDto countryReqDto) {
        System.out.println("save method of country service------------------------------------------------------");

        // why full qualified class name ?
        Country countryEntity = getCountryById(countryReqDto.getId());
        BeanUtils.copyProperties(countryReqDto,countryEntity);

        User managingDirectorDto = userService.getUserById(countryReqDto.getCountryManagerId());
        managingDirectorDto.setUserRole(roleService.findByRoleName("ROLE_TEAM_MANAGER"));


        countryEntity.setManagingDirector(managingDirectorDto);
        hibernateConfig.updateObject(countryEntity);
//        List<Player> playerList = new ArrayList<>();
//        playerList = playerService.allPlayers().stream()
//                .filter(player -> player.getUserInfo().getCountry().getId() == countryEntity.getId())
//                .collect(Collectors.toList());
//        countryEntity.setPlayerList(playerList);



        System.out.println("---------------------------------------------------");
        System.out.println("country entity is saved");
//        System.out.println(countryEntity);
    }

    public void addCountry(CountryReqDto countryReqDto){
        Country countryEntity = new com.rony.models.Country();
        BeanUtils.copyProperties(countryReqDto, countryEntity);
        Optional<Country> optionalCountry = Optional.empty();
        if(allCountries() != null ){
            optionalCountry = allCountries().stream()
                    .filter(country -> country.getCountryCode().equals(countryEntity.getCountryCode()))
                    .findFirst();
        }
        if(!optionalCountry.isPresent()){
            hibernateConfig.saveObject(countryEntity);
            logger.info("country saved successfully ! ");

        }else{
            logger.error("country code already exists !");
        }
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
