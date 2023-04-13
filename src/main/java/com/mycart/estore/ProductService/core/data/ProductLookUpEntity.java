package com.mycart.estore.ProductService.core.data;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "product-lookup")
public class ProductLookUpEntity{
    @Id
    private String productId;
    @Column(unique = true)
    private String title;
}
