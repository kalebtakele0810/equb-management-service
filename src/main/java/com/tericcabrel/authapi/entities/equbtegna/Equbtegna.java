
package com.tericcabrel.authapi.entities.equbtegna;

import com.tericcabrel.authapi.entities.equb.Equb;
import com.tericcabrel.authapi.entities.identity.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Table(name = "equbtegnas")
@Entity
@Getter
@Setter
@SuperBuilder
@RequiredArgsConstructor
public class Equbtegna {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true, length = 100, nullable = false)
    private String msisdn;

    @Column
    private int age;

    @Column
    private String gender;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}

