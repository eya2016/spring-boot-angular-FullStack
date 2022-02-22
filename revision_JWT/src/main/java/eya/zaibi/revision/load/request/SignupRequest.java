package eya.zaibi.revision.load.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

// DTO (image) Data Transfer Object - image de la class USER -
//class utilisé pour l'echange des données avec le USER
//a chaque entité on crée un DTO pour l'echange de données
public class SignupRequest {

    @NotBlank  // NotEmpty and @Size(min=1)
    @Size(max=20)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(min=6,max=20)
    private String password;

    private Set<String> role;

    public SignupRequest(){

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
}
