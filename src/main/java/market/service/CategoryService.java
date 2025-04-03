package market.service;

import java.util.List;
import market.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import market.model.Category;
@Service
public class CategoryService {
    
    public CategoryRepository categoryRepository;
    
    @Autowired
    public CategoryService(CategoryRepository c){
        categoryRepository =c;
    }
    
    public Category save(Category c){
        return categoryRepository.save(c);
    }
    
    public Category getById(long id){
        return categoryRepository.findById(id).orElse(null);
    }
    
    public List<Category> getAll(){
        return categoryRepository.findAll();
    }
    
    public void delete(Category c){
        categoryRepository.delete(c);
    }
    
    public void deleteById(long id){
        categoryRepository.deleteById(id);
    }
    
}
