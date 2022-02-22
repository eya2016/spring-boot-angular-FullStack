package eya.zaibi.revision.controller;

import eya.zaibi.revision.load.request.LoginRequest;
import eya.zaibi.revision.load.request.SignupRequest;
import eya.zaibi.revision.load.response.JwtResponse;
import eya.zaibi.revision.load.response.MessageResponse;
import eya.zaibi.revision.models.ERole;
import eya.zaibi.revision.models.Role;
import eya.zaibi.revision.models.User;
import eya.zaibi.revision.repository.RoleRepository;
import eya.zaibi.revision.repository.UserRepository;
import eya.zaibi.revision.security.jwt.JwtUtils;
import eya.zaibi.revision.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.PasswordAuthentication;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins ="*", allowedHeaders = "*")
public class AuthController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest request){

        // vérification de l'unicite de username
        if(userRepository.existsByUsername(request.getUsername()))
            return ResponseEntity.badRequest().body(
                    new MessageResponse("Error : Username is already in use !!!!!!")
            );

        // vérification de l'unicite de email
        if(userRepository.existsByEmail(request.getEmail()))
            return ResponseEntity.badRequest().body(
                    new MessageResponse("Error : Email is already in use !!!!!!")
            );

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));

        Set<Role> roles = new HashSet<>();

        Set<String> strroles = request.getRole();

        if(strroles ==null){
            Role roleuser = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(
                    ()->new RuntimeException("Role user not found !!!!")
            );
            roles.add(roleuser);
        }
        else {
            strroles.forEach(role ->{
                switch(role){
                    case "admin":
                        Role roleadmin = roleRepository.findByName(ERole.ROLE_ADMIN).orElseThrow(
                                ()->new RuntimeException("Role admin not found !!!!")
                        );
                        roles.add(roleadmin);
                        break;
                    case "secretaire":
                        Role rolesec = roleRepository.findByName(ERole.ROLE_SECRETAIRE).orElseThrow(
                                ()->new RuntimeException("Role secretaire not found !!!!")
                        );
                        roles.add(rolesec);
                        break;
                    default:
                        Role roleuser = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(
                                ()->new RuntimeException("Role user not found !!!!")
                        );
                        roles.add(roleuser);
                        break;
                }});
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok().body(
                new MessageResponse("User added successfully ")
        );
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@Valid @RequestBody LoginRequest request){

        // vérifier login et password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),
                        request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl details = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = details.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(
                new JwtResponse(
                        token,
                        details.getId(),
                        details.getUsername(),
                        details.getEmail(),
                        roles
                )
        );
    }
}
