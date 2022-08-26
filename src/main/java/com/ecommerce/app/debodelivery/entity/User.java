package com.ecommerce.app.debodelivery.entity;

import com.ecommerce.app.debodelivery.service.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    @Column(name = "password")
    private String password;
    @Column(name = "createdAt")
    private String createdAt;
    @Column(name = "loginToken")
    private String loginToken;
    @Column(name = "type")
    private Type type;
    @Column(name = "address")
    private String address;
    @Column(name = "isEmailVerified")
    private String isEmailVerified;
    @Column(name = "mobileNumber")
    private String mobileNumber;
}
