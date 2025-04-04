package market.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import market.model.Category;
import market.service.CategoryService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CategoryRest.class)
public class CategoryRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    private ObjectMapper objectMapper = new ObjectMapper();

    public Category create(long id) {
        Category c = new Category();
        c.setId(id);
        c.setName("Test");
        return c;
    }

    public CategoryRestTest() {
    }

    @Test
    public void testSave() throws Exception {
        Category c = create(1L);
        when(categoryService.save(any(Category.class))).thenReturn(c);
        mockMvc.perform(post("/category/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(c)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test"));
    }

    @Test
    public void testGetAll() {
    }

    @Test
    public void testGetById() throws Exception {
        Category c = create(1L);
        when(categoryService.getById(1L)).thenReturn(c);
        mockMvc.perform(get("/category/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    @Test
    public void testGetByIdNotFound() throws Exception {
        Category c = create(1L);
        when(categoryService.getById(1L)).thenReturn(null);
        mockMvc.perform(get("/category/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Categoria no encontrada"));
    }

    @Test
    public void testDeleteById() {
    }

}
