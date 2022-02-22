package eya.zaibi.revision.repository;

import eya.zaibi.revision.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    public List<Product> findByName(String name);
    // select * from products where name = :name
    public List<Product> findByNameAndMarque(String name, String marque);
    // select * from products where name = :name and marque = :marque

}
