package com.Digital.Commerce.System.Shopping.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Product")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="Id")
    private Integer id;

    @Column(name="Name")
    private String name;

    @Column(name="Description")
    private String Description;

    @Column(name="Price")
    private Long price;

    @Column(name="PictureUrl")
    private String pictureUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ProductBrandId", referencedColumnName = "Id")
    private Brand brand;

    private String productBrand;

    private String productType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ProductTypeId", referencedColumnName = "Id")
    private Type type;
}
