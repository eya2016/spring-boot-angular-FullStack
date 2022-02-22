package eya.zaibi.revision.security.services;


import eya.zaibi.revision.models.User;
import eya.zaibi.revision.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository agent;

    // cette méthode sera utilisée pour l'authentification et vous donne comme
    // résultat un objet User avec ces droits
    @Override
    public UserDetails loadUserByUsername(String entree) throws UsernameNotFoundException {

        User user = agent.findByUsername(entree).orElseThrow(
                () -> new UsernameNotFoundException("User not found with username :" + entree));
        // vérification du mot de passe en cachette

        return UserDetailsImpl.build(user);
    }
}
