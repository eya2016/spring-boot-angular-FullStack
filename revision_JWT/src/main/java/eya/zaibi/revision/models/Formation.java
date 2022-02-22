package eya.zaibi.revision.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="formations")
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String programm;
    private LocalDateTime start;
    private LocalDateTime end;

    @ManyToMany
    @JoinTable(name="participation",
            joinColumns = @JoinColumn(
                    name = "formation_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "employe_id",
                    referencedColumnName = "id"
            )
    )
    private List<Employe> employes;

}