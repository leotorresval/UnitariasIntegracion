package market.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import market.repository.ProductRepository;
import market.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    public ProductRepository productRepository;

    public Product saveProduct(Product p) {
        return productRepository.save(p);
    }

    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Product p) {
        return productRepository.save(p);
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(long id) {
        return productRepository.findById(id).orElse(null);
    }

}
