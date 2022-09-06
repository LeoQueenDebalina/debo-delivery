package com.ecommerce.app.debodelivery.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="OrderCancelDeters")
@Builder
public class OrderCancelDetails {
    @Id
    @Column(name = "cancelId")
    private String cancelId;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "checkoutId", referencedColumnName = "checkoutId")
    private Checkout checkout;
    @Column(name = "cancelReason")
    private String cancelReason;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
}
