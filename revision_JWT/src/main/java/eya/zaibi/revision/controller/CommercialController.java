package eya.zaibi.revision.controller;

import eya.zaibi.revision.exception.ResourceNotFound;
import eya.zaibi.revision.models.Commercial;
import eya.zaibi.revision.services.CommercialServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CommercialController {

    @Autowired
    CommercialServiceImpl agent;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/commercial")
    public List<Commercial> findAll(){
        return agent.findAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("commercial/{id}")
    public ResponseEntity<?> find(@PathVariable("id") long id) throws ResourceNotFound{

        Commercial comm = agent.findById(id).orElseThrow(() ->
                new ResourceNotFound("Commercial not found with id " + id));
        return ResponseEntity.ok().body(comm);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("commercial")
    public Commercial save(@Valid @RequestBody Commercial comm){
        return agent.saveorupdate(comm);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("commercial/{id}")
    public Commercial update(@PathVariable("id") long id, @Valid @RequestBody Commercial comm) throws ResourceNotFound{
        Commercial old = agent.findById(id).orElseThrow(()->
                new ResourceNotFound("commercial not found with id "+id));

        old.setFirstname(comm.getFirstname());
        old.setLastname(comm.getLastname());
        old.setPhone(comm.getPhone());
        return agent.saveorupdate(old);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("commercial/{id}")
    public Map<String, Object> delete(@PathVariable("id") long id) throws ResourceNotFound{

        Commercial comm = agent.findById(id).orElseThrow(()->
                new ResourceNotFound("commercial not found with id "+id));

        agent.deleteById(comm.getId());

        Map<String,Object> map = new HashMap<>();
        map.put("deleted ",Boolean.TRUE);
        map.put("Time", new Date());
        return map;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handlenotvalid(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errors.put(error.getField(), error.getDefaultMessage()));

        return errors;

    }

}
