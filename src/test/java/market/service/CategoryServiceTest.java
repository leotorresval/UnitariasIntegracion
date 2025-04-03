package market.service;

import java.util.List;
import java.util.Optional;
import market.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mock.*;
import market.model.Category;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    
    @Mock
    private CategoryRepository categoryRepository;
    
    @InjectMocks
    private CategoryService categoryService;
    
    public CategoryServiceTest() {
    }

    public Category create(long id){
        Category c = new Category();
        c.setId(id);
        c.setName("Test");
        return c;
    }
    
    @Test
    public void testSave() {
        Category c = create(1L);
        when(categoryRepository.save(any(Category.class))).thenReturn(c);
        Category result = categoryService.save(c);
        assertEquals(c.getName(), result.getName());
        verify(categoryRepository).save(c);
        
    }

    @Test
    public void testGetById() {
        Category c = create(1);
        when(categoryRepository.findById(1L)).thenReturn(Optional.of(c));
        Category result = categoryService.getById(1L);
        assertEquals(c.getName(), result.getName());
        
    }

    @Test
    public void testGetAll() {
        Category c1 =create(1);
        Category c2 =create(2);
        List<Category> l = List.of(c1,c2);
        when(categoryRepository.findAll()).thenReturn(l);
        List<Category> result = categoryService.getAll();
        assertNotNull(result);
        assertEquals(l.size(), result.size());
        
    }

    @Test
    public void testDelete() {
    }

    @Test
    public void testDeleteById() {
        Mockito.doNothing().when(categoryRepository).deleteById(1L);
        categoryService.deleteById(1L);
        verify(categoryRepository,Mockito.times(1)).deleteById(1L);
    }
    
}
