
package com.tericcabrel.authapi.dtos.equb;

import com.tericcabrel.authapi.entities.equb.EqubAgreement;
import com.tericcabrel.authapi.entities.equb.EqubCategory;
import com.tericcabrel.authapi.entities.equb.EqubType;
import com.tericcabrel.authapi.entities.equb.StartEqub;
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

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class AddEqub {
    private Integer id;
    private String name;
    private int amount;
    private int round;
    private Date started_date;
    private Date end_date;
    private String status;
    private List<Equbtegna> equbtegnas;
    private List<StartEqub> startEqubs;
    private EqubAgreement equbAgreement;
    private List<Payments> payments;

    private String equbType;
    private String equbCategory;
    private Date createdAt;
    private Date updatedAt;


}

