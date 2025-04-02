package market.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import market.model.Product;
import market.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductRest.class)
public class ProductRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testSaveProduct() throws Exception {
        Product p = new Product();
        p.setId(1);
        p.setName("Laptop");
        p.setDescription("Dell");
        p.setPrice(1200.0);
        p.setStock(5);

        Mockito.when(productService.saveProduct(Mockito.any(Product.class))).thenReturn(p);

        mockMvc.perform(post("/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Laptop"));
    }

    @Test
    public void testGetAllProducts() throws Exception {
        Product p = new Product();
        p.setId(1);
        p.setName("Laptop");
        p.setDescription("Dell");
        p.setPrice(1200.0);
        p.setStock(5);

        Mockito.when(productService.getAllProduct()).thenReturn(Arrays.asList(p));

        mockMvc.perform(get("/product/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Laptop"));
    }

    @Test
    public void testGetProductById() throws Exception {
        Product p = new Product();
        p.setId(1);
        p.setName("Tablet");
        p.setDescription("Samsung");
        p.setPrice(300.0);
        p.setStock(10);

        Mockito.when(productService.getProductById(1L)).thenReturn(p);

        mockMvc.perform(get("/product/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Tablet"));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product original = new Product();
        original.setId(1);
        original.setName("Tablet");
        original.setDescription("Samsung");
        original.setPrice(300.0);
        original.setStock(10);

        Product updated = new Product();
        updated.setId(1);
        updated.setName("Tablet Pro");
        updated.setDescription("Samsung");
        updated.setPrice(400.0);
        updated.setStock(12);

        Mockito.when(productService.getProductById(1L)).thenReturn(original);
        Mockito.when(productService.saveProduct(Mockito.any(Product.class))).thenReturn(updated);

        mockMvc.perform(put("/product/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Tablet Pro"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        Product p = new Product();
        p.setId(1);
        p.setName("Tablet");

        Mockito.when(productService.getProductById(1L)).thenReturn(p);
        Mockito.doNothing().when(productService).deleteProductById(p.getId());

        mockMvc.perform(delete("/product/1"))
                .andExpect(status().isOk());
    }
}
