package controller.supplier;

import javafx.collections.ObservableList;
import model.dto.SupplierDTO;

public interface SupplierService {
    public void add(String supplierID, String name, String companyName,String address,String city,String province,String postalCode,String phone,String email);
    public void delete(String supplierID);
    public void update(String supplierID, String name, String companyName,String address,String city,String province,String postalCode,String phone,String email);
    public ObservableList<SupplierDTO> loadTable();
}
