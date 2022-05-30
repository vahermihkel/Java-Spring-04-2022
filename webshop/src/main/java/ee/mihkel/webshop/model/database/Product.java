package ee.mihkel.webshop.model.database;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    private double price;

    private String imgSrc;
    private boolean active;
    private String description;
    private int stock;

    @NotNull
    @OneToOne
    private Subcategory category;
}

// 1 Product
// 2 Product
// 3 Product

// 1 Order
// 2 Order

// 4 Product
