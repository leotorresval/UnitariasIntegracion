
package market.service;

import java.util.List;
import market.model.Supplier;
import market.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class SupplierService {
        
    public SupplierRepository supplierRepository;
    
    @Autowired
    public SupplierService(SupplierRepository c){
        supplierRepository =c;
    }
    
    public Supplier save(Supplier c){
        return supplierRepository.save(c);
    }
    
    public Supplier getById(long id){
        return supplierRepository.findById(id).orElse(null);
    }
    
    public List<Supplier> getAll(){
        return supplierRepository.findAll();
    }
    
    public void delete(Supplier c){
        supplierRepository.delete(c);
    }
    
    public void deleteById(long id){
        supplierRepository.deleteById(id);
    }
    
}
