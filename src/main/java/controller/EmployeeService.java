package controller;

import javafx.collections.ObservableList;
import model.dto.EmployeeDTO;

public interface EmployeeService {

    public void addEmployeeDetails(String employeeId, String name,String nic, String dob, String position,double salary,String contactName, String address, String joinedDate, String status);
    public void delete(String empId);
    public void update(String employeeId, String name,String nic, String dob, String position,double salary,String contactName, String address, String joinedDate, String status);
    public ObservableList<EmployeeDTO> loadTable();
}
