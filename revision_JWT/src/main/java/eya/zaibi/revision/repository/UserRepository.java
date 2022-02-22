package eya.zaibi.revision.repository;

import eya.zaibi.revision.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username); // authentification
    Optional<User> findByEmail(String email); // authentification
    Boolean existsByUsername(String username); //subscribe
    Boolean existsByEmail(String email); //subscribe

}
