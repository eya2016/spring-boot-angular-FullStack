package eya.zaibi.revision.services;

import eya.zaibi.revision.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public Product saveorupdate(Product product);
    public List<Product> getAll();
    public void delete(long id);
    public Optional<Product> findById(Long id);
    public List<Product> search_name(String name);
    public List<Product> search_name_marque(String name,String marque);
}
