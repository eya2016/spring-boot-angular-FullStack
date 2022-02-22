package eya.zaibi.revision.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="ligneStore")
public class LigneStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name="store_id", referencedColumnName = "id")
    private Store store;

    @ManyToOne(optional = false)
    @JoinColumn(name="product_id", referencedColumnName = "id")
    private Product product;

    private int quantity;

}
