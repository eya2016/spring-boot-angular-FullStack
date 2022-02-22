package eya.zaibi.revision.repository;


import eya.zaibi.revision.models.ERole;
import eya.zaibi.revision.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    // select * from ROLE r where r.name = :name
    Optional<Role> findByName(ERole name); // objet / Exception --> null
}
