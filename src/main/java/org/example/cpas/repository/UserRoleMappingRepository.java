package org.example.cpas.repository;

import org.example.cpas.entities.UserRoleMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRoleMappingRepository extends JpaRepository<UserRoleMapping, Integer> {


    @Query(value = "select  role_id as roleId from user_role_mapping urm where  urm.user_id=?1",nativeQuery = true)
    public  Integer[] getAllRoleByUserId(Integer userId);

    @Modifying
    @Query(value = "delete from user_role_mapping where  user_id=?1 and role_id in (?2)",nativeQuery = true)
    public void  deleteMapping(Integer userId,Integer[] roleId);
}
