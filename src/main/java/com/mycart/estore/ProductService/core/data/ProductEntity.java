package com.mycart.estore.ProductService.core.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter
@Setter
@RequiredArgsConstructor
public class ProductEntity implements Serializable {

  public static final long serialVersionUID = -1275453453766313659L;

  @Id
  @Column(unique = true)
  private String productId;
  @Column(unique = true)
  private String title;
  private BigDecimal price;
  private Integer quantity;
}
