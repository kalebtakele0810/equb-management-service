
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

@Table(name = "start_equbs")
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class StartEqub {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;
    @Column(nullable = false)
    private int numberOfTimes;
    @UpdateTimestamp
    @Column(name = "joined_date")
    private Date joinedDate;
    @Column
    private int subGroup;
    @Column(nullable = false)
    private String status;
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;
}

