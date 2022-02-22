package eya.zaibi.revision.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name="commerciaux")
public class Commercial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message= "le nom est obligatoire")
    @Size(min=3, max=10,message="la taille doit etre in [3...10]")
    @Pattern(regexp ="[a-zA-Z]+", message="le nom doit etre composée de caractères")
    private String firstname;

    @NotEmpty(message= "le prenom est obligatoire")
    @Size(min=3, max=10,message="la taille doit etre in [3...10]")
    @Pattern(regexp ="[a-zA-Z]+", message="le prenom doit etre composée de caractères")
    private String lastname;

    @NotEmpty(message = "phone est obligatoire")
    @Size(min = 8, max = 13, message = "numéro de téléphone invalide")
    private String phone;
}
