package controller.customer;

import javafx.collections.ObservableList;
import model.dto.CustomerDTO;

public interface CustemerService {

    public void addCustomerDetails(String custID,String title,String name,String dob,double salary,String address,String city,String province,String postalCode);

    public void deleteCustomer(String custId);

    public void updateCustomer(String custID,String title,String name,String dob,double salary,String address,String city,String province,String postalCode);

    public ObservableList<CustomerDTO> loadTable();

}
