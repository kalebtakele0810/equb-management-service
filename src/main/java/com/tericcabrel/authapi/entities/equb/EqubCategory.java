
package com.tericcabrel.authapi.entities.equb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Table(name = "equb_category")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class EqubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String categoryRank;

    @Column(nullable = false)
    private String logo;
    @Column(nullable = false)
    private String code;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "equb_category_id", referencedColumnName = "id", nullable = true)
    private List<Equb> equbs;
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}

