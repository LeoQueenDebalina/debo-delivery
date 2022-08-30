package com.ecommerce.app.debodelivery.entity;

import com.ecommerce.app.debodelivery.eenum.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "User")
public class User {
    @Id
    @Column(name = "userId")
    private String userId;
    @Column(name = "userName")
    private String userName;
    @Column(name = "userEmail")
    private String userEmail;
    @Column(name = "mobileNumber")
    private String mobileNumber;
    @Column(name = "password")
    private String password;
    @Column(name = "createdAt")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Column(name = "loginToken")
    private String loginToken;
    @Column(name = "type")
    private Type type;
    @Column(name = "address")
    private String address;
    @Column(name = "isEmailVerified")
    private Boolean isEmailVerified;
    @Column(name = "isDeleted")
    private Boolean isDeleted;
}
