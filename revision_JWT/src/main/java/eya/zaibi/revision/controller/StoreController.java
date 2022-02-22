package eya.zaibi.revision.controller;

import eya.zaibi.revision.exception.ResourceNotFound;
import eya.zaibi.revision.models.Store;
import eya.zaibi.revision.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class StoreController {

    @Autowired
    StoreService agent;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/stores")
    public Store save(@Valid @RequestBody Store store){
        return agent.saveorupdate(store);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/stores")
    public List<Store> getStore(){
        return agent.getAll();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/stores/{id}")
    public Store getStoreById(@PathVariable("id") long id) throws ResourceNotFound{
         return agent.findById(id).orElseThrow(()-> new ResourceNotFound("Store not found with id : "+id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/stores/{id}")
    public Store updateStore(@PathVariable("id") long id, @RequestBody Store store) throws ResourceNotFound{
        Store old = agent.findById(id).orElseThrow(()-> new ResourceNotFound("Store not found with id : "+id));

        old.setName(store.getName());
        old.setCommercial(store.getCommercial());
        old.setListProduits(store.getListProduits());

        return agent.saveorupdate(old);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/stores/{id}")
    public Map<String, Boolean> deleteStore(@PathVariable("id") long id) throws ResourceNotFound{
        agent.findById(id).orElseThrow(()-> new ResourceNotFound("Store not found with id : "+id));
        agent.delete(id);

        Map<String, Boolean> res = new HashMap<>();
        res.put("Deleted :", Boolean.TRUE);
        return res;
    }
}
