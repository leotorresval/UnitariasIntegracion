package market.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
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
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Test"));
    }

    @Test
    public void testSave_InternalServerError() throws Exception {
        Category c = create(1L);
        doThrow(new RuntimeException("Exception"))
                .when(categoryService).save(any(Category.class));

        mockMvc.perform(post("/category/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(c)))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Error"));
    }


    @Test
    public void testGetAll() throws Exception {
        Category c1 = create(1);
        Category c2 = create(2);
        when(categoryService.getAll()).thenReturn(List.of(c1, c2));
        mockMvc.perform(get("/category/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"));

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
    public void testDeleteById() throws Exception {
        Category c = create(1);
        when(categoryService.getById(anyLong())).thenReturn(c);
        doNothing().when(categoryService).deleteById(anyLong());
        mockMvc.perform(delete("/category/1"))
                .andExpect(status().isOk());
        verify(categoryService).deleteById(anyLong());
    }
    
    @Test
    public void testDeleteByIdNotFound()throws Exception{
        Category c = create(1);
        when(categoryService.getById(anyLong())).thenReturn(null);
        mockMvc.perform(delete("/category/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Categoria no encontrada"));
    }

}
