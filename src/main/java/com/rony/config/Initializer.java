package com.rony.config;

import com.rony.enums.Genders;
import com.rony.enums.HomeTowns;
import com.rony.enums.Salutations;
import com.rony.enums.UserRole;
import com.rony.models.Country;
import com.rony.models.Player;
import com.rony.models.Role;
import com.rony.models.User;
import com.rony.requestDto.CountryReqDto;
import com.rony.requestDto.UserReqDto;
import com.rony.responseDto.CountryRespDto;
import com.rony.services.CountryService;
import com.rony.services.RoleService;
import com.rony.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Initializer {

    @Autowired
    private CountryService countryService;


    public Initializer(RoleService roleService, UserService userService, PasswordEncoder passwordEncoder,
                       HibernateConfig hibernateConfig, CountryService countryService) {
//        USER
//        PLAYER,
//        UMPIRE,
//        COACH,
//        TEAM_MANAGER,
//        ICC_AUTHORITY,
//        SUPER_ADMIN;

        roleService.create(new Role(System.nanoTime(), "ROLE_USER"));
        roleService.create(new Role(System.nanoTime(), "ROLE_ADMIN"));
        roleService.create(new Role(System.nanoTime(), "ROLE_PLAYER"));
        roleService.create(new Role(System.nanoTime(), "ROLE_UMPIRE"));
        roleService.create(new Role(System.nanoTime(), "ROLE_COACH"));
        roleService.create(new Role(System.nanoTime(), "ROLE_TEAM_MANAGER"));
        roleService.create(new Role(System.nanoTime(), "ROLE_ICC_AUTHORITY"));
        roleService.create(new Role(System.nanoTime(), "ROLE_SUPER_ADMIN"));

        CountryReqDto bd = new CountryReqDto();
        bd.setName("Bangladesh");
        bd.setCountryCode("BD");
        countryService.addCountry(bd);

        CountryReqDto india = new CountryReqDto();
        india.setName("India");
        india.setCountryCode("IND");
        countryService.addCountry(india);

        CountryReqDto china = new CountryReqDto();
        china.setName("China");
        china.setCountryCode("CHN");
        countryService.addCountry(china);

        // ICC_AUTH
        UserReqDto iccEntity = new UserReqDto();
        iccEntity.setId(System.nanoTime()+"");
        iccEntity.setName("i am icc");
        iccEntity.setEmail("icc@gmail.com");
        iccEntity.setAge("9999");
        iccEntity.setUserRole("ROLE_ICC_AUTHORITY");
        iccEntity.setPassword("1234");
        iccEntity.setUsername("icc");
        iccEntity.setGender(Genders.MALE.name());
        iccEntity.setHomeTown(HomeTowns.DHAKA.name());
        iccEntity.setSalutation(Salutations.MD.name());
        iccEntity.setMobile("1234");
        if(userService.getUserByEmail(iccEntity.getEmail())==null){
            userService.addUser(iccEntity);
        }

        // country manager
        UserReqDto cmEntity = new UserReqDto();
        cmEntity.setId(System.nanoTime()+"");
        cmEntity.setName("i am cm");
        cmEntity.setEmail("cm@gmail.com");
        cmEntity.setAge("9999");
        cmEntity.setUserRole("ROLE_TEAM_MANAGER");
        cmEntity.setPassword("1234");
        cmEntity.setDateOfBirth("1999-11-15");
        cmEntity.setUsername("country manager");
        cmEntity.setGender(Genders.MALE.name());
        cmEntity.setHomeTown(HomeTowns.DHAKA.name());
        cmEntity.setCountryId(countryService.getCountryByCode("BD").getId()+"");
        cmEntity.setSalutation(Salutations.MD.name());
        cmEntity.setMobile("1234");
        if(userService.getUserByEmail(cmEntity.getEmail())==null){
            userService.addUser(cmEntity);
        }

        // coach
        UserReqDto coachEntity = new UserReqDto();
        coachEntity.setId(System.nanoTime()+"");
        coachEntity.setName("i am coach");
        coachEntity.setEmail("coach@gmail.com");
        coachEntity.setAge("9999");
//      coach will be assigned by country manager, then role will be updated in service layer
        coachEntity.setUserRole("ROLE_USER");
        coachEntity.setPassword("1234");
        coachEntity.setUsername("coach");
        coachEntity.setDateOfBirth("1999-11-15");
        coachEntity.setGender(Genders.MALE.name());
        coachEntity.setCountryId(countryService.getCountryByCode("BD").getId()+"");
        coachEntity.setHomeTown(HomeTowns.DHAKA.name());
        coachEntity.setSalutation(Salutations.MD.name());
        coachEntity.setMobile("1234");
        if( userService.getUserByEmail(coachEntity.getEmail()) == null){
            userService.addUser(coachEntity);
        }

        // player
        UserReqDto playerEntity = new UserReqDto();
        playerEntity.setId(System.nanoTime()+"");
        playerEntity.setName("player");
        playerEntity.setEmail("player@gmail.com");
        playerEntity.setAge("9999");
        playerEntity.setUserRole("ROLE_PLAYER");
        playerEntity.setPassword("1234");
        playerEntity.setDateOfBirth("1999-11-15");
        playerEntity.setUsername("player");
        playerEntity.setGender(Genders.MALE.name());
        playerEntity.setHomeTown(HomeTowns.DHAKA.name());
        playerEntity.setCountryId(countryService.getCountryByCode("BD").getId()+"");
        playerEntity.setSalutation(Salutations.MD.name());
        playerEntity.setMobile("1234");
        if(userService.getUserByEmail(playerEntity.getEmail())==null){
            userService.addUser(playerEntity);
        }


        // player 1
        UserReqDto p1Entity = new UserReqDto();
        p1Entity.setId(System.nanoTime()+"");
        p1Entity.setName("player 1");
        p1Entity.setEmail("p1@gmail.com");
        p1Entity.setAge("9999");
        p1Entity.setUserRole("ROLE_USER");
        p1Entity.setPassword("1234");
        p1Entity.setDateOfBirth("1999-11-15");
        p1Entity.setUsername("player1");
        p1Entity.setGender(Genders.MALE.name());
        p1Entity.setCountryId(countryService.getCountryByCode("BD").getId()+"");
        p1Entity.setHomeTown(HomeTowns.DHAKA.name());
        p1Entity.setSalutation(Salutations.MD.name());
        p1Entity.setMobile("1234");
        if(userService.getUserByEmail(p1Entity.getEmail())==null){
            userService.addUser(p1Entity);
        }

        // player 2
        UserReqDto p2Entity = new UserReqDto();
        p2Entity.setId(System.nanoTime()+"");
        p2Entity.setName("player 2");
        p2Entity.setEmail("p2@gmail.com");
        p2Entity.setAge("9999");
        p2Entity.setUserRole("ROLE_USER");
        p2Entity.setPassword("1234");
        p2Entity.setDateOfBirth("1999-11-15");
        p2Entity.setUsername("player2");
        p2Entity.setGender(Genders.MALE.name());
        p2Entity.setHomeTown(HomeTowns.DHAKA.name());
        p2Entity.setCountryId(countryService.getCountryByCode("BD").getId()+"");
        p2Entity.setSalutation(Salutations.MD.name());
        p2Entity.setMobile("1234");
        if(userService.getUserByEmail(p2Entity.getEmail())==null){
            userService.addUser(p2Entity);
        }

        // player 3
        UserReqDto p3Entity = new UserReqDto();
        p3Entity.setId(System.nanoTime()+"");
        p3Entity.setName("player 3");
        p3Entity.setEmail("p4@gmail.com");
        p3Entity.setAge("9999");
        p3Entity.setUserRole("ROLE_USER");
        p3Entity.setPassword("1234");
        p3Entity.setDateOfBirth("1999-11-15");
        p3Entity.setUsername("player4");
        p3Entity.setGender(Genders.MALE.name());
        p3Entity.setCountryId(countryService.getCountryByCode("BD").getId()+"");
        p3Entity.setHomeTown(HomeTowns.DHAKA.name());
        p3Entity.setSalutation(Salutations.MD.name());
        p3Entity.setMobile("1234");
        if(userService.getUserByEmail(p3Entity.getEmail())==null){
            userService.addUser(p3Entity);
        }


    }// end initializer

}// end class
