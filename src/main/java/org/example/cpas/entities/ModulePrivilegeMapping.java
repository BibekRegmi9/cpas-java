package org.example.cpas.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "module_privilege_mappings")
@NoArgsConstructor
@Getter
@Setter
public class ModulePrivilegeMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "code")
    private String code;

    @Column(name = "is_active")
    private boolean is_active;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "moduleId", referencedColumnName = "id")
    private Module module;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "privilegeId", referencedColumnName = "id")
    private Privilege privilege;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}
