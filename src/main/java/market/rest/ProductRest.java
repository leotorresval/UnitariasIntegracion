package market.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import market.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import market.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/product/")
public class ProductRest {

    @Autowired
    public ProductService productService;

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product p) {
        try {
            Product product = productService.saveProduct(p);
            return ResponseEntity.created(new URI("/product/" + product.getId())).body(product);
        } catch (URISyntaxException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, @RequestBody Product updatedProduct) {
        Product existing = productService.getProductById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());
        existing.setStock(updatedProduct.getStock());
        return ResponseEntity.ok(productService.saveProduct(existing));
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.getAllProduct());
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        Product product = productService.getProductById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok().build();
    }
}
