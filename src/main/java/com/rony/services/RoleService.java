package com.rony.services;

import com.rony.config.HibernateConfig;
import com.rony.models.Role;
import com.rony.models.User;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private HibernateConfig hibernateConfig;

    public RoleService(HibernateConfig hibernateConfig) {
        this.hibernateConfig = hibernateConfig;
    }

    public Role create(Role role){
        if(exists(role.getAuthority())) {
            return null;
        }else {
            save(role);
            return role;
        }
    }

    public void save(Role role){
        var session = hibernateConfig.getSession();
        Transaction tx = session.getTransaction();
        if(!tx.isActive()){
            tx = session.beginTransaction();
        }
        var roleEntity = new Role();
        BeanUtils.copyProperties(role,roleEntity);
        session.save(roleEntity);
        session.flush();
        tx.commit();
    }

    public Role findByRoleName(String roleName){
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Role.class);
        var root = criteriaQuery.from(Role.class);
        criteriaQuery.select(root);
        criteriaQuery.where(criteriaBuilder.equal(root.get("roleName"),roleName));

        var result =  hibernateConfig.getSession()
                .getEntityManagerFactory()
                .createEntityManager()
                .createQuery(criteriaQuery)
                .getResultList();
        if(result.size()==0){
            return null;
        }else {
            return result.get(0);
        }
    }

    public boolean exists(String role) {
        if(findByRoleName(role)!=null) {
            return true;
        }else {
            return false;
        }
    }

    public List<Role> listAllRoles(){
        var criteriaBuilder = hibernateConfig.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Role.class);
        var root = criteriaQuery.from(Role.class);
        criteriaQuery.select(root);

        return hibernateConfig.getSession()
                .getEntityManagerFactory()
                .createEntityManager()
                .createQuery(criteriaQuery)
                .getResultList();
    }
}
