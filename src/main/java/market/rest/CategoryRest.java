package market.rest;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import market.model.Category;
import market.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/category/")
public class CategoryRest {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category>
            save(@RequestBody Category c) {
        Category result = categoryService.save(c);
        return ResponseEntity.ok(result);

    }
            
    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }
    
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable long id){
        Category c = categoryService.getById(id);
        if(c!=null)
            return ResponseEntity.ok(c);
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Categoria no encontrada");
        }
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id){
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
