package market.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import market.model.Category;
import market.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
public class CategoryRestTestIntegration {

    @Autowired
    public MockMvc mockmvc;

    @Autowired
    public ObjectMapper objectMapper;
    
    @Autowired
    public CategoryService categoryService;
    
    @BeforeEach
    public void setUp(){
        Category c1= create(1);
        Category c2= create(2);
        categoryService.save(c1);
        categoryService.save(c2);
    }

    public Category create(long id) {
        Category c = new Category();
        c.setId(id);
        c.setName("Test");
        return c;
    }

    @Test
    public void save() throws Exception {
        Category c = create(150);
        mockmvc.perform(post("/category/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(c)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test"));

    }

    @Test
    public void getById() throws Exception {
        mockmvc.perform(get("/category/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test"));
    }

    @Test
    public void getByIdNotFound() throws Exception {
        mockmvc.perform(get("/category/300"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Categoria no encontrada"));
    }

    @Test
    public void getAll() {

    }

    @Test
    public void deleteById() throws Exception{
        mockmvc.perform(delete("/category/1"))
                .andExpect(status().isOk());
    }

}
