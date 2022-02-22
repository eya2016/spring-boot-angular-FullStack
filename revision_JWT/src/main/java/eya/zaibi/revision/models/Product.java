package eya.zaibi.revision.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="produits")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String marque;
    private float price;
    private int quantity;

    @ManyToOne(optional = false)
    @JoinColumn(name="store_id", referencedColumnName = "id")
    private Store store;

}
