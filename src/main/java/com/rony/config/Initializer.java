package com.rony.config;

import com.rony.enums.UserRole;
import com.rony.models.Role;
import com.rony.models.User;
import com.rony.services.RoleService;
import com.rony.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Initializer {
    public Initializer(RoleService roleService, UserService userService, PasswordEncoder passwordEncoder) {
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
        if(userService.getUserByEmail(playerEntity.getEmail())==null){
            userService.addUser(playerEntity);
        }


        // ICC_AUTH
        User iccEntity = new User();
        iccEntity.setId(System.nanoTime());
        iccEntity.setName("icc");
        iccEntity.setEmail("icc@gmail.com");
        iccEntity.setAge("9999");
//        iccEntity.setDateOfBirth(new Date());
        iccEntity.setUserRole(roleService.findByRoleName("ROLE_ICC_AUTHORITY"));
        iccEntity.setPassword("1234");
        iccEntity.setUsername("icc");
        if(userService.getUserByEmail(iccEntity.getEmail())==null){
            userService.addUser(iccEntity);
        }

        // SUPER ADMIN
        User suAdminEntity = new User();
        suAdminEntity.setId(System.nanoTime());
        suAdminEntity.setName("super admin");
        suAdminEntity.setEmail("suadmin@gmail.com");
        suAdminEntity.setAge("9999");
//        userEntity.setDateOfBirth(new Date());
        suAdminEntity.setUserRole(roleService.findByRoleName("ROLE_SUPER_ADMIN"));
        suAdminEntity.setPassword("1234");
        suAdminEntity.setUsername("suadmin");
        if(userService.getUserByEmail(suAdminEntity.getEmail())==null){
            userService.addUser(suAdminEntity);
        }

    }
}
