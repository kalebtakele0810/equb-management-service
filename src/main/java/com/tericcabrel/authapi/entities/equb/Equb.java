
package com.tericcabrel.authapi.entities.equb;

import com.tericcabrel.authapi.entities.equbtegna.Equbtegna;
import com.tericcabrel.authapi.entities.payment.Payments;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "equb_id", referencedColumnName = "id", nullable = true)
    private List<Equbtegna> equbtegnas;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "equb_id", referencedColumnName = "id", nullable = true)
    private List<StartEqub> startEqubs;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "agreement_id", referencedColumnName = "id", nullable = false)
    private EqubAgreement equbAgreement;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "equb_id", referencedColumnName = "id", nullable = true)
    private List<Payments> payments;
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}

