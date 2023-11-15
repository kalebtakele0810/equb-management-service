
package com.tericcabrel.authapi.entities.equb;

import com.tericcabrel.authapi.entities.equbtegna.Equbtegna;
import com.tericcabrel.authapi.entities.identity.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Table(name = "equbs")
@Entity
@Getter
@Setter
@SuperBuilder
public class Equb {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String type;

    @Column(length = 100, nullable = false)
    private String duration;

    @Column(nullable = false)
    private String status;

    @Column
    private int gender;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "equb_id", referencedColumnName = "id", nullable = true)
    private List<Equbtegna> equb;
}

