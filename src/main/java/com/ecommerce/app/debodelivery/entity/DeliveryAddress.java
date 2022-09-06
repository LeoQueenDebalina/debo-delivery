package com.ecommerce.app.debodelivery.entity;

import com.ecommerce.app.debodelivery.util.AddressType;
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
@Table(name="DeliveryAddress")
@Builder
public class DeliveryAddress {
    @Id
    @Column(name = "deliveredAddressId")
    private String deliveredAddressId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "phoneNumber")
    private String phoneNumber;
    @Column(name = "pinCode")
    private Integer pinCode;
    @Column(name = "state")
    private String state;
    @Column(name = "city")
    private String city;
    @Column(name = "houseNo")
    private String houseNo;
    @Column(name = "roadName")
    private String roadName;
    @Column(name = "addressType")
    @Enumerated(EnumType.STRING)
    private AddressType addressType;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "isDeleted")
    private Boolean isDeleted;
}
