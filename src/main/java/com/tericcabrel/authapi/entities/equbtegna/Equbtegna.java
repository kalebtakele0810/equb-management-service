package com.tericcabrel.authapi.entities.equbtegna;

import com.tericcabrel.authapi.entities.equb.StartEqub;
import com.tericcabrel.authapi.entities.payment.Payments;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

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
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(unique = true, nullable = false)
    private String msisdn;
    @Column
    private int age;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenderEnum gender;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "equbtegna_id", referencedColumnName = "id")
    private List<StartEqub> startEqubs;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "equbtegna_id", referencedColumnName = "id")
    private List<Payments> payments;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}

