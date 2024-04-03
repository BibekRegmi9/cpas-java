package org.example.cpas.repository;


import org.example.cpas.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select u.id,\n" +
            "       u.user_name,\n" +
            "       u.citizenship_no,\n" +
            "       u.email,\n" +
            "       u.gender,\n" +
            "       u.phone,\n" +
            "       CASE\n" +
            "           WHEN COUNT(r.id) > 0 THEN\n" +
            "                json_agg(\n" +
            "                    json_build_object(\n" +
            "                        'id', r.id,\n" +
            "                        'name', r.name\n" +
            "                    )\n" +
            "                )\n" +
            "           ELSE\n" +
            "                NULL\n" +
            "       END as roles\n" +
            "from users u\n" +
            "left join user_role_mapping ur on u.id = ur.user_id\n" +
            "left join roles r on ur.role_id = r.id\n" +
            "group by u.id, u.user_name, u.citizenship_no, u.email, u.email, u.gender;", nativeQuery = true)
    String findAllUsersWithRoles();



}
