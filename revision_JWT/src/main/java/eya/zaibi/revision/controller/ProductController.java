package eya.zaibi.revision.controller;

import eya.zaibi.revision.dto.ProductDto;
import eya.zaibi.revision.exception.ResourceNotFound;
import eya.zaibi.revision.mapper.ProductDtoMapper;
import eya.zaibi.revision.models.Product;
import eya.zaibi.revision.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ProductController {

    @Autowired
    ProductService agent;

    @Autowired
    ProductDtoMapper mapper;

    @PreAuthorize("hasRole('ADMIN') or hasRole('SECRETAIRE')")
    @PostMapping("/products")
    public Product save(@Valid @RequestBody Product p){
        return agent.saveorupdate(p);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SECRETAIRE')")
    @GetMapping("/products")
    public List<ProductDto> liste(){
        return mapper.ListModelToDto(agent.getAll());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SECRETAIRE')")
    @GetMapping("/products/{id}")
    public ProductDto getProduct(@PathVariable("id") long id) throws ResourceNotFound {
        Product product = agent.findById(id).orElseThrow(
                ()->new ResourceNotFound("Product not found for id:"+id)
        );
        return mapper.ModelToDto(product);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SECRETAIRE')")
    @PutMapping("/product/{id}")
    public ProductDto update(@PathVariable("id") long id, @RequestBody Product p) throws ResourceNotFound{
        Product old = agent.findById(id).orElseThrow(()-> new ResourceNotFound("Store not found with id : "+id));

        old.setName(p.getName());
        old.setMarque(p.getMarque());
        old.setPrice(p.getPrice());
        old.setStore(p.getStore());

        return mapper.ModelToDto(old);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('SECRETAIRE')")
    @DeleteMapping("/product/{id}")
    public Map<String, Boolean> delete(@PathVariable("id") long id) throws ResourceNotFound{
        Product p = agent.findById(id).orElseThrow(()-> new ResourceNotFound("product not found "+id));
        agent.delete(id);
        Map<String, Boolean> res = new HashMap<>();
        res.put("deleted :",Boolean.TRUE);
        return res;
    }

}
