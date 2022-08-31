package com.ecommerce.app.debodelivery.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ProductImage")
public class ProductImage {
    @Id
    @Column(name = "imageId")
    private String imageId;
    @Column(name = "imageName")
    private String imageName;
    @Column(name = "imageType")
    private String imageType;
    @Lob
    @Column(name = "imageData")
    private byte[] imageData;
}
