package eya.zaibi.revision.services;

import eya.zaibi.revision.models.Product;
import eya.zaibi.revision.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository agent;

    @Override
    public Product saveorupdate(Product product) {
        return agent.save(product);
    }

    @Override
    public List<Product> getAll() {
        return agent.findAll();
    }

    @Override
    public void delete(long id) {
        agent.deleteById(id);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return agent.findById(id);
    }

    @Override
    public List<Product> search_name(String name) {
        return agent.findByName(name);
    }

    @Override
    public List<Product> search_name_marque(String name, String marque) {
        return agent.findByNameAndMarque(name, marque);
    }
}
