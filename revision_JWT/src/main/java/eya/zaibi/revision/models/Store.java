package eya.zaibi.revision.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="stores")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private List<Product> listProduits;

    @OneToOne(optional = false)
    @JoinColumn(name = "commercial_id",referencedColumnName = "id")
    private Commercial commercial;

}
