
package com.tericcabrel.authapi.entities.equb;

import com.tericcabrel.authapi.entities.equbtegna.Equbtegna;
import com.tericcabrel.authapi.entities.identity.RoleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Table(name = "equb_type")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class EqubType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EqubTypeEnum name;

    @Column(nullable = false)
    private int numberOfDays;

    @Column(nullable = false)
    private String status;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "equb_type_id", referencedColumnName = "id", nullable = true)
    private List<Equb> equbs;
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}

