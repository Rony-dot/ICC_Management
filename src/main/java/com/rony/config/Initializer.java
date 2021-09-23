package com.rony.config;

import com.rony.enums.Genders;
import com.rony.enums.HomeTowns;
import com.rony.enums.Salutations;
import com.rony.enums.UserRole;
import com.rony.models.Country;
import com.rony.models.Player;
import com.rony.models.Role;
import com.rony.models.User;
import com.rony.services.CountryService;
import com.rony.services.RoleService;
import com.rony.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

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

//        admin pass before set to database
//        $2a$10$WTqRJFGDatjtYhDHFmkzNeTf8/8g8Ot7JC1AQUsgASqnSJ7UTWM5K

//        fetch from database admin pass
//        $2a$10$7RaHAJAavX0VvJe/qwwkR.EKFMm2QFT9NkSViEhHvq2wFuR9czBxC

//        copied from database admin pass
//        $2a$10$7RaHAJAavX0VvJe/qwwkR.EKFMm2QFT9NkSViEhHvq2wFuR9czBxC


        roleService.create(new Role(System.nanoTime(), "ROLE_USER"));
        roleService.create(new Role(System.nanoTime(), "ROLE_ADMIN"));
        roleService.create(new Role(System.nanoTime(), "ROLE_PLAYER"));
        roleService.create(new Role(System.nanoTime(), "ROLE_UMPIRE"));
        roleService.create(new Role(System.nanoTime(), "ROLE_COACH"));
        roleService.create(new Role(System.nanoTime(), "ROLE_TEAM_MANAGER"));
        roleService.create(new Role(System.nanoTime(), "ROLE_ICC_AUTHORITY"));
        roleService.create(new Role(System.nanoTime(), "ROLE_SUPER_ADMIN"));



        // country manager
        User cmEntity = new User();
        cmEntity.setId(System.nanoTime());
        cmEntity.setName("i am cm");
        cmEntity.setEmail("cm@gmail.com");
        cmEntity.setAge("9999");
//        userEntity.setDateOfBirth(new Date());
        cmEntity.setUserRole(roleService.findByRoleName("ROLE_TEAM_MANAGER"));
        cmEntity.setPassword("1234");
        cmEntity.setUsername("country manager");
        cmEntity.setGender(Genders.MALE.name());
        cmEntity.setHomeTown(HomeTowns.DHAKA.name());
        cmEntity.setSalutation(Salutations.MD.name());
        cmEntity.setMobile("1234");
        if(userService.getUserByEmail(cmEntity.getEmail())==null){
            userService.addUser(cmEntity);
        }

        // admin
        User adminEntity = new User();
        adminEntity.setId(System.nanoTime());
        adminEntity.setName("admin");
        adminEntity.setEmail("admin@gmail.com");
        adminEntity.setAge("9999");
//        adminEntity.setDateOfBirth(new Date());
        adminEntity.setUserRole(roleService.findByRoleName("ROLE_ADMIN"));
        adminEntity.setPassword("1234");
        adminEntity.setUsername("admin");
        adminEntity.setGender(Genders.MALE.name());
        adminEntity.setHomeTown(HomeTowns.DHAKA.name());
        adminEntity.setSalutation(Salutations.MD.name());
        adminEntity.setMobile("1234");
        if(userService.getUserByEmail(adminEntity.getEmail()) == null){
            userService.addUser(adminEntity);
        }


        // player
        User playerEntity = new User();
        playerEntity.setId(System.nanoTime());
        playerEntity.setName("player");
        playerEntity.setEmail("player@gmail.com");
        playerEntity.setAge("9999");
//        userEntity.setDateOfBirth(new Date());
        playerEntity.setUserRole(roleService.findByRoleName("ROLE_PLAYER"));
        playerEntity.setPassword("1234");
        playerEntity.setUsername("player");
        playerEntity.setGender(Genders.MALE.name());
        playerEntity.setHomeTown(HomeTowns.DHAKA.name());
        playerEntity.setSalutation(Salutations.MD.name());
        playerEntity.setMobile("1234");
        if(userService.getUserByEmail(playerEntity.getEmail())==null){
            userService.addUser(playerEntity);
        }


        // ICC_AUTH
        User iccEntity = new User();
        iccEntity.setId(System.nanoTime());
        iccEntity.setName("i am icc");
        iccEntity.setEmail("icc@gmail.com");
        iccEntity.setAge("9999");
//        iccEntity.setDateOfBirth(new Date());
        iccEntity.setUserRole(roleService.findByRoleName("ROLE_ICC_AUTHORITY"));
        iccEntity.setPassword("1234");
        iccEntity.setUsername("icc");
        iccEntity.setGender(Genders.MALE.name());
        iccEntity.setHomeTown(HomeTowns.DHAKA.name());
        iccEntity.setSalutation(Salutations.MD.name());
        iccEntity.setMobile("1234");
        if(userService.getUserByEmail(iccEntity.getEmail())==null){
            userService.addUser(iccEntity);
        }

        // SUPER ADMIN
//        User suAdminEntity = new User();
//        suAdminEntity.setId(System.nanoTime());
//        suAdminEntity.setName("super admin");
//        suAdminEntity.setEmail("suadmin@gmail.com");
//        suAdminEntity.setAge("9999");
////        userEntity.setDateOfBirth(new Date());
//        suAdminEntity.setUserRole(roleService.findByRoleName("ROLE_SUPER_ADMIN"));
//        suAdminEntity.setPassword("1234");
//        suAdminEntity.setUsername("suadmin");
//        if(userService.getUserByEmail(suAdminEntity.getEmail())==null){
//            userService.addUser(suAdminEntity);
//        }

        // coach
        User coachEntity = new User();
        coachEntity.setId(System.nanoTime());
        coachEntity.setName("i am coach");
        coachEntity.setEmail("coach@gmail.com");
        coachEntity.setAge("9999");
//      coach will be assigned by country manager, then role will be updated in service layer
        coachEntity.setUserRole(roleService.findByRoleName("ROLE_USER"));
        coachEntity.setPassword("1234");
        coachEntity.setUsername("coach");
        coachEntity.setGender(Genders.MALE.name());
        coachEntity.setHomeTown(HomeTowns.DHAKA.name());
        coachEntity.setSalutation(Salutations.MD.name());
        coachEntity.setMobile("1234");
        if( userService.getUserByEmail(coachEntity.getEmail()) == null){
            userService.addUser(coachEntity);
        }

//

        // player 1
        User p1Entity = new User();
        p1Entity.setId(System.nanoTime());
        p1Entity.setName("player 1");
        p1Entity.setEmail("p1@gmail.com");
        p1Entity.setAge("9999");
//        userEntity.setDateOfBirth(new Date());
        p1Entity.setUserRole(roleService.findByRoleName("ROLE_USER"));
        p1Entity.setPassword("1234");
        p1Entity.setUsername("player1");
        p1Entity.setGender(Genders.MALE.name());
        p1Entity.setHomeTown(HomeTowns.DHAKA.name());
        p1Entity.setSalutation(Salutations.MD.name());
        p1Entity.setMobile("1234");
        if(userService.getUserByEmail(p1Entity.getEmail())==null){
            userService.addUser(p1Entity);
        }

        // player 2
        User p2Entity = new User();
        p2Entity.setId(System.nanoTime());
        p2Entity.setName("player 2");
        p2Entity.setEmail("p2@gmail.com");
        p2Entity.setAge("9999");
//        userEntity.setDateOfBirth(new Date());
        p2Entity.setUserRole(roleService.findByRoleName("ROLE_USER"));
        p2Entity.setPassword("1234");
        p2Entity.setUsername("player2");
        p2Entity.setGender(Genders.MALE.name());
        p2Entity.setHomeTown(HomeTowns.DHAKA.name());
        p2Entity.setSalutation(Salutations.MD.name());
        p2Entity.setMobile("1234");
        if(userService.getUserByEmail(p2Entity.getEmail())==null){
            userService.addUser(p2Entity);
        }

        // player 3
        User p3Entity = new User();
        p3Entity.setId(System.nanoTime());
        p3Entity.setName("player 3");
        p3Entity.setEmail("p4@gmail.com");
        p3Entity.setAge("9999");
//        userEntity.setDateOfBirth(new Date());
        p3Entity.setUserRole(roleService.findByRoleName("ROLE_USER"));
        p3Entity.setPassword("1234");
        p3Entity.setUsername("player4");
        p3Entity.setGender(Genders.MALE.name());
        p3Entity.setHomeTown(HomeTowns.DHAKA.name());
        p3Entity.setSalutation(Salutations.MD.name());
        p3Entity.setMobile("1234");
        if(userService.getUserByEmail(p3Entity.getEmail())==null){
            userService.addUser(p3Entity);
        }



    }
}
