package com.ecommerce.app.debodelivery.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ConfirmationToken")
@Builder
public class ConfirmationToken {
    @Id
    @Column(name = "tokenId")
    private String tokenId;
    @Column(name = "token")
    private String token;
    @Column(name = "createdAt")
    private LocalDateTime createdAt;
    @Column(name = "expiresAt")
    private LocalDateTime expiresAt;
    @Column(name = "confirmedAt")
    private LocalDateTime confirmedAt;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
}
