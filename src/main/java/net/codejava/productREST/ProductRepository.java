package net.codejava.productREST;

import net.codejava.productREST.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT * FROM Product WHERE name= :firstname", nativeQuery = true)
    public List<Product> findByName(@Param("firstname") String name);
}
