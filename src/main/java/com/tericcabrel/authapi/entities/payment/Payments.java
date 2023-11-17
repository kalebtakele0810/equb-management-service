
package com.tericcabrel.authapi.entities.payment;

import com.tericcabrel.authapi.entities.equbtegna.GenderEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Table(name = "payments")
@Entity
@Getter
@Setter
@SuperBuilder
@RequiredArgsConstructor
public class Payments {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ChannelEnum channel;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private String transactionId;
    @UpdateTimestamp
    @Column(name = "payment_date")
    private Date paymentDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatusEnum status;
    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date updatedAt;

}

