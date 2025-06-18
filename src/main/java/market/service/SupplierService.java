package market.service;

import java.util.List;
import market.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import market.model.Supplier;
@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    public Supplier saveSupplier(Supplier p) {
        return supplierRepository.save(p);
    }

    public void deleteSupplierById(long id) {
        supplierRepository.deleteById(id);
    }

    public Supplier updateSupplier(Supplier p) {
        return supplierRepository.save(p);
    }

    public List<Supplier> getAllSupplier() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(long id) {
        return supplierRepository.findById(id).orElse(null);
    }
}
