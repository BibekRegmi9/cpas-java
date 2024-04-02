package org.example.cpas.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "user_role_mapping")
@NoArgsConstructor
@Getter
@Setter
public class UserRoleMapping {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "is_active")
    private boolean is_active;

    @Column(name = "user_id")
    private int user_id;

    @Column(name = "role_id")
    private int role_id;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}
