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
@Table(name="OrderFeedback")
@Builder
public class OrderFeedback {
    @Id
    @Column(name = "feedBackId")
    private String feedBackId;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "checkoutId", referencedColumnName = "checkoutId")
    private Checkout checkout;
    @Column(name = "feedBackMessage")
    private String feedBackMessage;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
}
