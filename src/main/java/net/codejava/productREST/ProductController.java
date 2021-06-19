package net.codejava.productREST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/")
    public String homePage(){
        return "welcome page";
    }

    @GetMapping("/products")
    public List<Product> list(){
        return service.listAll();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> get(@PathVariable Integer id){
        try {
            Product product = service.get(id);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/products1/{name}")
    public List<Integer> listByName(@PathVariable String name){
        return service.listByName(name);
    }

    @PostMapping("/products")
    public void add(@RequestBody Product product){
        service.save(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> update(@RequestBody Product product,@PathVariable Integer id){
        try{
            Product existProduct = service.get(id);
            service.save(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try{
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
