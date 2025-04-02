package market.service;

import market.model.Product;
import market.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ProductServiceTest {

    private ProductService productService;
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        productRepository = mock(ProductRepository.class);
        productService = new ProductService();
        productService.productRepository = productRepository; // acceso público en tu clase
    }

    @Test
    public void testSaveProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("Laptop");
        product.setPrice(999.0);
        product.setStock(10);

        when(productRepository.save(ArgumentMatchers.any(Product.class))).thenReturn(product);

        Product saved = productService.saveProduct(product);

        assertNotNull(saved);
        assertEquals("Laptop", saved.getName());
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product();
        product.setId(2);

        doNothing().when(productRepository).deleteById(product.getId());

        productService.deleteProductById(product.getId());

        verify(productRepository, times(1)).deleteById(product.getId());
    }

    @Test
    public void testUpdateProduct() {
        Product product = new Product();
        product.setId(3);
        product.setName("Tablet");
        product.setPrice(300.0);

        when(productRepository.save(product)).thenReturn(product);

        Product updated = productService.updateProduct(product);

        assertEquals("Tablet", updated.getName());
        verify(productRepository).save(product);
    }

    @Test
    public void testGetAllProducts() {
        Product p1 = new Product();
        p1.setId(1);
        p1.setName("A");

        Product p2 = new Product();
        p2.setId(2);
        p2.setName("B");

        when(productRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Product> list = productService.getAllProduct();

        assertEquals(2, list.size());
        verify(productRepository).findAll();
    }

    @Test
    public void testGetProductById() {
        Product p = new Product();
        p.setId(1);
        p.setName("Monitor");

        when(productRepository.findById(1L)).thenReturn(Optional.of(p));

        Product found = productService.getProductById(1L);

        assertNotNull(found);
        assertEquals("Monitor", found.getName());
        verify(productRepository).findById(1L);
    }
}
